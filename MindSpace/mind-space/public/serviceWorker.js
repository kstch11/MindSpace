
// Files to cache
const VERSION = 'v1';
const CACHE_NAME = `period-tracker-${VERSION}`;
const APP_STATIC_RESOURCES = [
    '/',
    '/index.html',
];

self.addEventListener("install", (e) => {
    e.waitUntil((async () => {
            const cache = await caches.open("cacheName_identifier");
            cache.addAll([
                '/',
                '/index.html',
            ]);
        })()
    );
});

self.addEventListener("install", (event) => {
    event.waitUntil(
        (async () => {
            const cache = await caches.open(CACHE_NAME);
            cache.addAll(APP_STATIC_RESOURCES);
        })(),
    );
});

self.addEventListener("activate", (event) => {
    event.waitUntil(
        (async () => {
            const names = await caches.keys();
            await Promise.all(
                names.map((name) => {
                    if (name !== CACHE_NAME) {
                        return caches.delete(name);
                    }
                }),
            );
            await clients.claim();
        })(),
    );
});

self.addEventListener("fetch", (event) => {
    // As a single page app, direct app to always go to cached home page.
    if (event.request.mode === "navigate") {
        event.respondWith(caches.match("/"));
        return;
    }

    event.respondWith(
        (async () => {
            const cache = await caches.open(CACHE_NAME);
            const cachedResponse = await cache.match(event.request.url);
            if (cachedResponse) {
                // Return the cached response if it's available.
                return cachedResponse;
            }
            // If resource isn't in the cache, return a 404.
            return new Response(null, { status: 404 });
        })(),
    );
});

self.addEventListener('fetch', function(event) {
    event.respondWith(
        fetch(event.request).catch(function() {
            self.registration.showNotification("No Internet Connection", {
                body: "You are currently offline. Some features may not be available.",
            });
        })
    );
});