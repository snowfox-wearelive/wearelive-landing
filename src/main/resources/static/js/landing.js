// WEARELIVE landing — progressive enhancement shared by both audience pages.
// The form posts only to the endpoint rendered into its own page (data-endpoint), so a page never
// submits to the other audience's API.
(() => {
  'use strict';

  const EMAIL_PATTERN = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  const RESERVE_PARAM = 'reserve';

  const dialog = document.getElementById('pre-register');
  const form = dialog?.querySelector('form');
  const errorBox = form?.querySelector('[data-form-error]');
  const submitButton = form?.querySelector('[data-submit]');
  const toast = document.querySelector('[data-toast]');
  const topButton = document.querySelector('[data-top-button]');

  // ── Modal ──────────────────────────────────────────────
  function openModal() {
    if (!dialog || dialog.open) return;
    dialog.showModal();
    document.body.style.overflow = 'hidden';
    form.elements.name.focus();
  }

  function closeModal() {
    if (dialog?.open) dialog.close();
  }

  if (dialog) {
    document.querySelectorAll('[data-reserve-open]').forEach((el) => el.addEventListener('click', openModal));
    dialog.querySelector('[data-reserve-close]').addEventListener('click', closeModal);
    dialog.addEventListener('click', (e) => {
      if (e.target === dialog) closeModal();
    });
    dialog.addEventListener('close', () => {
      document.body.style.overflow = '';
      showError(null);
    });

    // Arriving via the other audience's modal (`?reserve=1`): open ours and clean the URL.
    const url = new URL(window.location.href);
    if (url.searchParams.get(RESERVE_PARAM) === '1') {
      url.searchParams.delete(RESERVE_PARAM);
      history.replaceState(null, '', url.pathname + url.search + url.hash);
      openModal();
    }
  }

  // ── Form ───────────────────────────────────────────────
  function showError(message) {
    if (!errorBox) return;
    errorBox.textContent = message ?? '';
    errorBox.hidden = !message;
  }

  function validate(data) {
    if (!data.name) return '이름을 입력해주세요.';
    if (!EMAIL_PATTERN.test(data.email)) return '올바른 이메일 주소를 입력해주세요.';
    if (!data.marketingConsent) return '소식 수신에 동의해주세요.';
    return null;
  }

  form?.addEventListener('submit', async (e) => {
    e.preventDefault();
    const data = Object.fromEntries(new FormData(form));
    data.name = (data.name ?? '').trim();
    data.email = (data.email ?? '').trim();
    data.marketingConsent = form.elements.marketingConsent.checked;

    const invalid = validate(data);
    if (invalid) return showError(invalid);
    showError(null);

    submitButton.disabled = true;
    const label = submitButton.textContent;
    submitButton.textContent = '처리 중…';
    try {
      const res = await fetch(form.dataset.endpoint, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json', Accept: 'application/json' },
        body: JSON.stringify(data),
      });
      if (res.ok) {
        form.reset();
        closeModal();
        showToast();
        return;
      }
      const body = await res.json().catch(() => null);
      showError(body?.message ?? '잠시 후 다시 시도해주세요.');
    } catch {
      showError('네트워크 연결을 확인한 뒤 다시 시도해주세요.');
    } finally {
      submitButton.disabled = false;
      submitButton.textContent = label;
    }
  });

  // ── Toast ──────────────────────────────────────────────
  let toastTimer;
  function showToast() {
    if (!toast) return;
    toast.hidden = false;
    clearTimeout(toastTimer);
    toastTimer = setTimeout(() => (toast.hidden = true), 4000);
  }
  toast?.addEventListener('click', () => (toast.hidden = true));

  // ── TOP button ─────────────────────────────────────────
  if (topButton) {
    const update = () => {
      const visible = window.scrollY > 600;
      topButton.classList.toggle('is-visible', visible);
      topButton.tabIndex = visible ? 0 : -1;
    };
    update();
    window.addEventListener('scroll', update, { passive: true });
    topButton.addEventListener('click', () => window.scrollTo({ top: 0 }));
  }
})();
