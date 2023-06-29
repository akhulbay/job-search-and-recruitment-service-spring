let jobId = document.getElementById("candidatesJobId").value;
let candidatesListDiv = document.getElementById("candidatesList");

let candidatesFirstName = document.getElementById("candidatesFirstName");
let candidatesLastName = document.getElementById("candidatesLastName");
let candidatesLocation = document.getElementById("candidatesLocation");
let candidatesExperience = document.getElementById("candidatesExperience");
let candidatesDegree = document.getElementById("candidatesDegree");

getAllCandidates();

function getAllCandidates() {
    const httpRequest = new XMLHttpRequest();
    httpRequest.open("GET", `/user-job-applications?userProfileId=&jobId=${jobId}`, true);
    httpRequest.onreadystatechange = async () => {
        if (httpRequest.readyState === XMLHttpRequest.DONE) {
            if (httpRequest.status === 200) {
                let candidatesList = JSON.parse(httpRequest.responseText);
                console.log(candidatesList)
                candidatesListDiv.innerHTML = await setAllCandidates(candidatesList)
            } else {
                let error = httpRequest.responseText;
                console.log(error)
            }
        }
    }
    httpRequest.send();
}

async function getProfileImage(candidateProfileId) {
    const httpRequest = new XMLHttpRequest();
    httpRequest.open("GET", "/user-profiles/" + candidateProfileId + "/avatar", true);
    httpRequest.responseType = "arraybuffer";
    httpRequest.send();

    return new Promise((resolve, reject) => {
        httpRequest.onreadystatechange = () => {
            if (httpRequest.readyState === XMLHttpRequest.DONE) {
                if (httpRequest.status === 200) {
                    const imageBytes = new Uint8Array(httpRequest.response);
                    const blob = new Blob([imageBytes], {type: "image/jpeg"});

                    resolve(URL.createObjectURL(blob));
                } else {
                    let error = httpRequest.responseText;
                    console.log(error)
                    reject(error)
                }
            }
        }
    })
}

function filterCandidates() {
    let result = "";
    if (candidatesFirstName.value !== '') {
        result += `&firstName=${candidatesFirstName.value}`
    }
    if (candidatesLastName.value !== '') {
        result += `&lastName=${candidatesLastName.value}`
    }
    result += `&location=${candidatesLocation.value}&experience=${candidatesExperience.value}&degree=${candidatesDegree.value}`
    const httpRequest = new XMLHttpRequest();
    httpRequest.open("GET", `/user-job-applications?userProfileId=&jobId=${jobId}` + result, true);
    httpRequest.onreadystatechange = async () => {
        if (httpRequest.readyState === XMLHttpRequest.DONE) {
            if (httpRequest.status === 200) {
                let candidatesList = JSON.parse(httpRequest.responseText);
                console.log(candidatesList)
                candidatesListDiv.innerHTML = await setAllCandidates(candidatesList)
            } else {
                let error = httpRequest.responseText;
                console.log(error)
            }
        }
    }
    httpRequest.send();
}

async function setAllCandidates(candidatesList) {
    let result = '';
    for (let i = 0; i < candidatesList.length; i++) {
        let imageUrl = await getProfileImage(candidatesList[i].userProfile.id);
        result += `
        <div class="col-lg-12">
                            <div class="bookmark-post card mt-4">
                                <div class="card-body">
                                    <div class="d-flex mb-4">
                                        <div class="flex-shrink-0 position-relative">
                                            <img src="${imageUrl}" alt="" class="avatar-md rounded" id="candidate">
                                        </div>
                                        <div class="ms-3">
                                            <a href="/candidate-details/${candidatesList[i].userProfile.user.id}"
                                               class="primary-link"><h5 class="fs-17">${candidatesList[i].userProfile.user.firstName} ${candidatesList[i].userProfile.user.lastName}</h5></a>
                                            <span class="badge bg-soft-info fs-13">${candidatesList[i].userProfile.accountType}</span>
                                        </div>
                                    </div>
                                    <div class="border rounded mb-4">
                                        <div class="row g-0">
                                            <div class="col-lg-6">
                                                <div class="border-end px-3 py-2">
                                                    <p class="text-muted mb-0">Exp. : ${candidatesList[i].userProfile.experienceYears} Years</p>
                                                </div>
                                            </div><!--end col-->
                                            <div class="col-lg-6">
                                                <div class="px-3 py-2">
                                                    <p class="text-muted mb-0">${candidatesList[i].userProfile.degree}</p>
                                                </div>
                                            </div><!--end col-->
                                        </div><!--end row-->
                                    </div>
                                    <p class="text-muted">${candidatesList[i].coverLetter}</p>
                                    <div class="mt-3">
<!--                                        <a href="#hireNow" data-bs-toggle="modal"-->
<!--                                           class="btn btn-primary btn-hover w-100 mt-2"><i-->
<!--                                                class="mdi mdi-account-check"></i> Hire Now</a>-->
                                        <a href="/candidate-details/${candidatesList[i].userProfile.user.id}"
                                           class="btn btn-soft-primary btn-hover w-100 mt-2"><i class="mdi mdi-eye"></i>
                                            View Profile</a>
                                    </div>
                                </div>
                            </div> <!--end card-->
                        </div><!--end col-->
        `;
    }
    if (result.length === 0) {
        result = '<h6>No candidates for this job!</h6>'
    }
    return new Promise((resolve, reject) => {
        resolve(result);
    });
}