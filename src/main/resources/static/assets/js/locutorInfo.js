const infoButtons = document.querySelectorAll('.info-button');
const popups = document.querySelectorAll('.popup-locutor');

infoButtons.forEach((button, index) => {
  const loremText = button.dataset.lorem;
  const popup = popups[index];

  button.addEventListener('mouseover', () => {
    popup.classList.add('show');
  });

  button.addEventListener('mouseout', () => {
    popup.classList.remove('show');
  });
});
