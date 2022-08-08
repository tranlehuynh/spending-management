const images = document.querySelectorAll('.main-img');
const width = images[0].clientWidth;

function autoSlider() {
    let i = 0;
    setInterval(() => {
        if (i === images.length - 1) {
            i = 0;
            images[i].style.marginLeft = `0px`;
            images[i + 1].style.marginLeft = `0px`;
        } else {
            images[i].style.marginLeft = `${-width}px`;
            i++;
        }
    }, 2000);
}

autoSlider();


