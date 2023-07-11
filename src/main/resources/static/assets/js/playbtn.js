const audio = document.getElementById("audio");
const playPause = document.getElementById("play");

class AudioPlayer {
  constructor() {
    this.audio = audio;
    this.playPause = playPause;
    this.pauseBtn = playPause.querySelector(".pause-btn");
    this.playBtn = playPause.querySelector(".play-btn");
  }

  playAudio() {
    if (this.audio.paused || this.audio.ended) {
      this.pauseBtn.classList.toggle("hide");
      this.playBtn.classList.toggle("hide");
      this.audio.src = "assets/audio/intromod.mp3";
      this.audio.play();

      // Esperar a que termine de reproducir el audio de introducción
      this.audio.addEventListener("ended", () => {
        this.audio.src = "https://cp9.serverse.com/proxy/perfor?mp=/stream;";
        this.audio.play();
      });
    } else {
      this.audio.pause();
      this.pauseBtn.classList.toggle("hide");
      this.playBtn.classList.toggle("hide");
    }
  }
}

const audioPlayer = new AudioPlayer();

playPause.addEventListener("click", () => {
  audioPlayer.playAudio();
});
