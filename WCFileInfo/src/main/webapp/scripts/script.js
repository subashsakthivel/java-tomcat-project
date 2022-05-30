const form = document.querySelector("form"),
    fileInput = document.querySelector(".file-input"),
    progressArea = document.querySelector(".progress-area"),
    uploadedArea = document.querySelector(".uploaded-area");
const loading = document.querySelector(".load");

loading.addEventListener("click", ()=> {
    let xhr = new XMLHttpRequest();
    xhr.open("GET", "file-upload-servlet");
})

form.addEventListener("click", () =>{
    fileInput.click();
});

fileInput.onchange = ({target})=>{
    let file = target.files[0]; 
    if(file){
        let fileName = file.name;
        if(fileName.length >= 12){ 
            let splitName = fileName.split('.');
            fileName = splitName[0].substring(0, 13) + "... ." + splitName[1];
        }
        uploadFile(fileName); //calling uploadFile with passing file name as an argument
    }
}


function uploadFile(name){
    let xhr = new XMLHttpRequest(); 
    xhr.open("POST", "file-upload-servlet"); 
    xhr.upload.addEventListener("progress", ({loaded, total}) =>{ 
        let fileLoaded = Math.floor((loaded / total) * 100);  
        let fileTotal = Math.floor(total / 1000); 
        let fileSize;
        // if file size is less than 1024 then add only KB else convert this KB into MB
        (fileTotal < 1024) ? fileSize = fileTotal + " KB" : fileSize = (loaded / (1024*1024)).toFixed(2) + " MB";
        let progressHTML = `<li class="row">
                          <i class="fas fa-file-alt"></i>
                          <div class="content">
                            <div class="details">
                              <span class="name">${name} • Uploading</span>
                              <span class="percent">${fileLoaded}%</span>
                            </div>
                            <div class="progress-bar">
                              <div class="progress" style="width: ${fileLoaded}%"></div>
                            </div>
                          </div>
                        </li>`;
     
        uploadedArea.classList.add("onprogress");
        progressArea.innerHTML = progressHTML;
        if(loaded == total){
            progressArea.innerHTML = "";
            let uploadedHTML = `<li class="row">
                            <div class="content upload">
                              <i class="fas fa-file-alt"></i>
                              <div class="details">
                                <span class="name">${name} • Uploaded</span>
                                <span class="size">${fileSize}</span>
                              </div>
                            </div>
                            <i class="fas fa-check"></i>
                          </li>`;
            uploadedArea.classList.remove("onprogress");
           
            uploadedArea.insertAdjacentHTML("afterbegin", uploadedHTML); 
        }
    });
    let data = new FormData(form);
    xhr.send(data); //sending form data
}
