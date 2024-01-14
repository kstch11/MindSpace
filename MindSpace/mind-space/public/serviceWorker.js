// Files to cache
const VERSION = 'v1';
const CACHE_NAME = `period-tracker-${VERSION}`;
const APP_STATIC_RESOURCES = [
    'index.html',
];

self.addEventListener("install", (e) => {
    console.log('installing service worker')
    e.waitUntil(
        caches.open(CACHE_NAME).then(cache => {
            return cache.addAll([
                `/`,
                `index.html`,
                `static/js/bundle.js`
            ])
                .then(() => self.skipWaiting());
        })
    )
});

self.addEventListener('activate', event =>{
    console.log('activating');
    event.waitUntil(self.clients.claim());
})

self.addEventListener('fetch', function (event) {
    if (navigator.onLine) {
        let fetchRequest = event.request.clone();
        return fetch(fetchRequest).then((
            function (response) {
                if (!response || response.status !== 200 || response.type !== 'basic') {
                    return response;
                }

                let responseToCache = response.clone();
                caches.open(CACHE_NAME)
                    .then(function (cache) {
                        cache.put(event.request, responseToCache);
                    });

                return response;
            }
        ))
    } else {
        event.respondWith(
            caches.match(event.request)
                .then(
                    function (response) {
                        if (response) {
                            return response;
                        }
                    }
                )
        );
    }
})