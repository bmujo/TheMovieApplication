# TheMovieApplication


The Movie Application is a simple project that uses [The Movie DB API](https://developers.themoviedb.org/3/getting-started/introduction) and shows trending movies and TV shows. You can also search for movies and TV shows. There is also details view for both.

## Tech Stack

This project uses Android Architecture Components, recommended architecture for building apps

## Development setup

- You require the latest Android Studio 4.2.2 (stable channel) to be able to build the app.
- Kotlin version 1.5.10


### Libraries
* [Android Architecture Components][arch]
* [Retrofit][retrofit] for REST api communication
* [Glide][glide] for loading gifs
* [Paging][paging] for pagination
* [Hilt][hilt] for dependency injection
* [GSON][gson] for JSON desiralization
* MVVM

[arch]: https://developer.android.com/arch
[paging]: https://developer.android.com/topic/libraries/architecture/paging/v3-overview
[retrofit]: http://square.github.io/retrofit
[glide]: https://github.com/bumptech/glide
[hilt]: https://developer.android.com/training/dependency-injection/hilt-android
[gson]: https://github.com/google/gson

### API keys

You need to supply API / client key for the service the app uses.

- [THE MOVIE DB](https://developers.themoviedb.org/3/getting-started/introduction)

Once you obtain the key, you can set them in your api/ApiClientInterface
