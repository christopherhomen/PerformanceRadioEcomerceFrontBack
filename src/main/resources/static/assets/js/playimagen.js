//si funciona con locutores, usando youtube, pero a veces no muestra la imagen correcta 
const h2Element = document.querySelector('h2.cc_streaminfo');
const imgElement = document.querySelector('img.player__img');
let prevText = null;

setInterval(async () => {
    const text = h2Element.textContent;
    if (text !== prevText) {
        prevText = text;
        const words = text.split(' ').filter(word => word.toLowerCase() !== 'desconocido');

        let foundImage = false;
        for (const word of words) {
            const imgPath = `assets/img/locutor/${word.toLowerCase()}.JPG`;
            if (imageExists(imgPath)) {
                imgElement.src = imgPath;
                console.log(`Image found: ${imgPath}`);
                foundImage = true;
                break;
            }
        }

        if (!foundImage) {
            for (let i = 0; i < words.length; i++) {
                for (let j = i + 1; j <= words.length; j++) {
                    const wordCombination = words.slice(i, j).join('_').toLowerCase();
                    const imgPath = `assets/img/locutor/${wordCombination}.JPG`;
                    if (imageExists(imgPath)) {
                        imgElement.src = imgPath;
                        console.log(`Image found: ${imgPath}`);
                        foundImage = true;
                        break;
                    }
                }
                if (foundImage) break;
            }
        }

        if (!foundImage) {
            const response = await fetch(`https://www.googleapis.com/youtube/v3/search?part=snippet&q=${words.join('+')}&type=video&key=AIzaSyCO5F3yenpdk4j1zknsu3rn3NKYzoTvbBA`);
            const data = await response.json();
            const videoThumbnail = data.items[0].snippet.thumbnails.high.url;
            if (videoThumbnail) {
                imgElement.src = videoThumbnail;
                console.log(`Video thumbnail found: ${videoThumbnail}`);
            }
        }
    }
}, 1000);

function imageExists(imageUrl) {
    const http = new XMLHttpRequest();
    http.open('HEAD', imageUrl, false);
    http.send();
    return http.status !== 404;
}