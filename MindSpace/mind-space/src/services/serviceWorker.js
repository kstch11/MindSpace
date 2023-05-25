self.importScripts('data/games.js');

// Files to cache
const cacheName = 'MindSpace-v1';
const appShellFiles = [
    '/',
    '/index.html',
    '/clientProfile',
];
// const gamesImages = [];
// for (let i = 0; i < games.length; i++) {
//     gamesImages.push(`data/img/${games[i].slug}.jpg`);
// }
// const contentToCache = appShellFiles.concat(gamesImages);

// Installing Service Worker
self.addEventListener('install', (e) => {
    console.log('[Service Worker] Install');
    e.waitUntil((async () => {
        const cache = await caches.open(cacheName);
        console.log('[Service Worker] Caching all: app shell and content');
        await cache.addAll(appShellFiles);
    })());
});

// Fetching content using Service Worker
self.addEventListener('fetch', (e) => {
    // Cache http and https only, skip unsupported chrome-extension:// and file://...
    if (!(
        e.request.url.startsWith('http:') || e.request.url.startsWith('https:')
    )) {
        return;
    }

    e.respondWith((async () => {
        const r = await caches.match(e.request);
        console.log(`[Service Worker] Fetching resource: ${e.request.url}`);
        if (r) return r;
        const response = await fetch(e.request);
        const cache = await caches.open(cacheName);
        console.log(`[Service Worker] Caching new resource: ${e.request.url}`);
        await cache.put(e.request, response.clone());
        return response;
    })());
});




// importScripts('https://storage.googleapis.com/workbox-cdn/releases/6.0.2/workbox-sw.js');
//
// self.addEventListener('install', (event) => {
//     const urlsToCache = [
//         '/',
//         '/index.html',
//         '/clientProfile',
//     ];
//
//     const cacheName = 'my-app-cache';
//
//     event.waitUntil(
//         caches.open(cacheName).then((cache) => {
//             return cache.addAll(urlsToCache);
//         })
//     );
// });
//
// self.addEventListener('fetch', (event) => {
//     event.respondWith(
//         caches.match(event.request).then((response) => {
//             return response || fetch(event.request);
//         })
//     );
// });
