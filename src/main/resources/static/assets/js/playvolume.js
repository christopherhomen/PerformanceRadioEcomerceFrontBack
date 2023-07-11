const rangeInput = document.getElementById('range');
const audioPlayerS = document.getElementById('audio');
const volumeMuteIcon = document.querySelector('.fa-volume-mute');
const volumeUpIcon = document.querySelector('.fa-volume-up');

rangeInput.addEventListener('input', function() {
  const volume = parseFloat(this.value) / 100;
  audioPlayerS.volume = volume;

  // Actualizar el estilo de la barra de volumen
  const fillPercentage = volume * 100;
  rangeInput.style.background = `linear-gradient(to right, #5c87fe ${fillPercentage}%, #d7dbdd ${fillPercentage}%)`;

  if (volume === 0) {
    volumeMuteIcon.style.display = 'inline';
    volumeUpIcon.style.display = 'none';
  } else if (volume < 0.5) {
    volumeMuteIcon.style.display = 'none';
    volumeUpIcon.style.display = 'inline';
    volumeUpIcon.classList.add('fa-volume-down');
    volumeUpIcon.classList.remove('fa-volume-up');
  } else {
    volumeMuteIcon.style.display = 'none';
    volumeUpIcon.style.display = 'inline';
    volumeUpIcon.classList.remove('fa-volume-down');
    volumeUpIcon.classList.add('fa-volume-up');
  }

  console.log('Volumen:', volume);
});

// Inicializar el estado inicial de los iconos de volumen
volumeMuteIcon.style.display = 'none';
volumeUpIcon.style.display = 'inline';

console.log('Archivo JavaScript cargado correctamente.');
