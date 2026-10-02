// Sanity check for the two static landing pages (no dependencies): `node scripts/check-pages.mjs`
//  - every local href/src points to a file that exists
//  - each page's pre-registration buttons (header, hero, final CTA) go to its own Google Form only
//  - neither page uses the other audience's assets
import { existsSync, readFileSync } from 'node:fs';

const BAND_FORM = '1k9AXAFiB6ekKuRddNuYwUhi_EzoPl2kFMu6Gv_m4BY8';
const OWNER_FORM = '1S69UsyIjmmL6baxdhBonj4veTa6yZE4fCjFLa_l6gJY';

const PAGES = [
  { file: 'index.html', form: BAND_FORM, otherForm: OWNER_FORM, otherAssets: 'assets/owner/' },
  { file: 'owner.html', form: OWNER_FORM, otherForm: BAND_FORM, otherAssets: 'assets/band/' },
];

const errors = [];

for (const page of PAGES) {
  const html = readFileSync(page.file, 'utf8');

  for (const [, url] of html.matchAll(/(?:href|src)="([^"]+)"/g)) {
    if (/^(https?:|#|mailto:)/.test(url)) continue;
    if (!existsSync(url.split(/[?#]/)[0])) errors.push(`${page.file}: missing file ${url}`);
  }

  const formLinks = [...html.matchAll(/href="(https:\/\/docs\.google\.com\/forms\/[^"]+)"/g)].map((m) => m[1]);
  if (formLinks.length !== 3 || !formLinks.every((url) => url.includes(page.form))) {
    errors.push(`${page.file}: expected 3 pre-registration links to its own form, got ${JSON.stringify(formLinks)}`);
  }
  if (html.includes(page.otherForm)) errors.push(`${page.file}: links to the other audience's form`);
  if (html.includes(page.otherAssets)) errors.push(`${page.file}: uses ${page.otherAssets}`);
}

if (errors.length) {
  console.error(errors.join('\n'));
  process.exit(1);
}
console.log('Pages OK');
