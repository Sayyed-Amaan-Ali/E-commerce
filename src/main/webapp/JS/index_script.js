document.addEventListener('DOMContentLoaded', () => {
  const loginBtn = document.getElementById('loginBtn');
  const signupBtn = document.getElementById('SignupBtn');
  const adminBtn = document.getElementById('AdminBtn');

  const loginPopup = document.getElementById('login_Popup');
  const signupPopup = document.getElementById('Signup_Popup');
  const adminPopup = document.getElementById('Adminlogin_Popup');

  const closeButtons = document.querySelectorAll('.close');

  // Open popups
  loginBtn.addEventListener('click', () => togglePopup(loginPopup));
  signupBtn.addEventListener('click', () => togglePopup(signupPopup));
  adminBtn.addEventListener('click', () => togglePopup(adminPopup));

  // Close popups
  closeButtons.forEach(btn => {
    btn.addEventListener('click', (e) => {
      e.target.parentElement.parentElement.style.display = 'none';
    });
  });

  function togglePopup(popup) {
    popup.style.display = popup.style.display === 'block' ? 'none' : 'block';
    popup.querySelector('.popup-content').classList.add('animate-popup');
  }
});
