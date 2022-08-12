// Get the modal
var modal = document.getElementById("myModal");
var modal1 = document.getElementById("myModal1");
// Get the button that opens the modal
var btn = document.getElementById("myBtn");
// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];
var span1 = document.getElementsByClassName("close1")[0];
// When the user clicks on the button, open the modal
btn.onclick = function () {
    modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function () {
    modal.style.display = "none";
}

span1.onclick = function () {
    modal1.style.display = "none";
}

const categoryButton = document.querySelector('.category-wallet')
const ex = document.querySelector('.expense-button-click')
const myIn = document.querySelector('.income-button-click')
const exItem = document.querySelector('.expense-items')
const inItem = document.querySelector('.income-items')
const navIcon = document.querySelector('.nav-i-mobile')
const navbar = document.querySelector('.navbar-haha')
const navIconHihi = document.querySelector('.ayda')

// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }

    // if (event.target == navbar) {
    //   navbar.classList.remove('show-navbar')
    // }

}

categoryButton.onclick = function () {
    modal1.style.display = "block";
}


ex.onclick = () => {
    exItem.style.marginLeft = '0px'
}

myIn.onclick = () => {
    exItem.style.marginLeft = '-507px'
}

navIcon.onclick = () => {
    navbar.classList.add('show-navbar')
}

navIconHihi.onclick = () => {
    navbar.classList.remove('show-navbar')
}


