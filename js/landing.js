// WEARELIVE landing — progressive enhancement shared by both audience pages.
// Pre-registration buttons are plain links to each audience's own Google Form, so no script is involved there.
(() => {
  'use strict';

  // ── TOP button ─────────────────────────────────────────
  const topButton = document.querySelector('[data-top-button]');
  if (!topButton) return;

  const update = () => {
    const visible = window.scrollY > 600;
    topButton.classList.toggle('is-visible', visible);
    topButton.tabIndex = visible ? 0 : -1;
  };
  update();
  window.addEventListener('scroll', update, { passive: true });
  topButton.addEventListener('click', () => window.scrollTo({ top: 0 }));
})();
