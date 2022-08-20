// Get the modal
var modal = document.getElementById("myModal");
var modal1 = document.getElementById("myModal1");
// Get the button that opens the modal
var btn = document.getElementById("myBtn");
// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];
var span1 = document.getElementsByClassName("close1")[0];

const header = document.querySelector('.header');
const navbar = document.querySelector('.navbar-haha');
const mainContent = document.querySelector('.main-content');

window.onload = () => {
    if (window.location.pathname === "/SpendingManagement/") {
        navbar.classList.add('mother-nav');
        header.style.display = "none";
    } else if (window.location.pathname === "/SpendingManagement/register") {
        navbar.classList.add('mother-nav');
        header.style.display = "none";
    } else if (window.location.pathname === "/SpendingManagement/admin/user-manage") {
        header.style.display = "none";
        mainContent.style.top = '10px';
        mainContent.style.minHeight = '97vh';
    } else if (window.location.pathname === "/SpendingManagement/dashboard/wallet-user") {
        const buttonWalletUser = document.getElementById('myBtnUser');
        const myButtonWallet = document.getElementById('myBtnWallet');
        const myButton = document.getElementById('myBtn');
        const walletHehe = document.querySelector('.wallet-info-text');
        const test = document.querySelector('.wallet-hehehe');
        buttonWalletUser.style.display = 'block';
        myButton.style.display = 'none';
        myButtonWallet.style.display = 'none';
        walletHehe.style.display = 'none';
        test.style.display = 'flex';
        test.style.alignItems = 'center'; 
    }   else {
        navbar.classList.remove('mother-nav');
    }
};

// When the user clicks on the button, open the modal
btn.onclick = function () {
    modal.style.display = "block";
};

// When the user clicks on <span> (x), close the modal
span.onclick = function () {
    modal.style.display = "none";
};

span1.onclick = function () {
    modal1.style.display = "none";
};

const categoryButton = document.querySelector('.category-wallet');
const ex = document.querySelector('.expense-button-click');
const myIn = document.querySelector('.income-button-click');
const exItem = document.querySelector('.expense-items');
const inItem = document.querySelector('.income-items');
const navIcon = document.querySelector('.nav-i-mobile');
const navIconHihi = document.querySelector('.ayda');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
};

categoryButton.onclick = function () {
    modal1.style.display = "block";
};


ex.onclick = () => {
    exItem.style.marginLeft = '0px';
};

myIn.onclick = () => {
    exItem.style.marginLeft = '-507px';
};

navIcon.onclick = () => {
    navbar.classList.add('show-navbar');
};

navIconHihi.onclick = () => {
    navbar.classList.remove('show-navbar');
};


//This is choose category
let itemDiv = document.querySelectorAll('.okey-con-de-hihi');
let categoryWallet = document.querySelector('.category-wallet-div');
let categoryInput = document.querySelector('.category-p-input');
let categoryDateInput = document.querySelector('.category-p-date');
for (let i = 0; i < itemDiv.length; i++) {
    itemDiv[i].onclick = () => {
        let myImage = itemDiv[i].children[0].src;
        let myText = itemDiv[i].children[1].outerText;

        categoryWallet.children[0].src = myImage;

        categoryInput.value = myText;
        categoryInput.placeholder = myText;

        modal1.style.display = 'none';
    };
}

let walletModalDiv = document.querySelectorAll('.wallet-modal-div');
let walletPInput = document.querySelector('.wallet-p-input');
let walletPInputNone = document.querySelector('.wallet-p-input-none');
let walletModalSpan = document.querySelectorAll('.wallet-modal-span');
let myModal2 = document.getElementById('myModal2');

for (let i = 0; i < walletModalDiv.length; i++) {
    walletModalDiv[i].onclick = () => {
        walletPInput.value = walletModalSpan[i].outerText;
        walletPInput.placeholder = walletModalSpan[i].outerText;
        
        walletPInputNone.value = walletModalSpan[i].id;
        myModal2.style.display = 'none';     
    };
}

let modelShowToHide = document.querySelector('.add-model-box');
let modelShowHideDivHen = document.querySelector('.add-model-box-div');
let childchild = document.querySelectorAll('.add-model-box-div-child');
let belloMinion = document.querySelectorAll(".this-is-my-input");

let ohMyChuoi = document.querySelector('.wallet-hehehe');

ohMyChuoi.onclick = () => {
    modelShowHideDivHen.classList.toggle('show-model-box-div');
};


for (let i = 0; i < childchild.length; i++) {
    childchild[i].onclick = () => {
        belloMinion[i].click();
    }
}


let modalTopItem = document.querySelector('.modal-top-item');
let span2 = document.querySelector('.close2');

modalTopItem.onclick = () => {
    myModal2.style.display = 'block';
};

span2.onclick = () => {
    myModal2.style.display = 'none';
};

let myModal3 = document.getElementById('myModal3');
let span3 = document.querySelector('.close3');
let myBtnWallet = document.getElementById('myBtnWallet');

let myModal4 = document.getElementById('myModal4');
let span4 = document.querySelector('.close4');
let iDontKnow = document.getElementById('myBtnUser');

myBtnWallet.onclick = () => {
    myModal3.style.display = 'block';
};

span3.onclick = () => {
    myModal3.style.display = 'none';
};

iDontKnow.onclick = () => {
    myModal4.style.display = 'block';
};

span4.onclick = () => {
    myModal4.style.display = 'none';
};






