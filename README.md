# Art Institute of Chicago Virtual Tour
This app uses the Art Institute of Chicago's API to display all of the art available in the museum.
NOTE: This app uses their public API but was not endorsed or otherwise affiliated with the AIC.

# Introduction
This app is written entirely in Kotlin and uses the Model-View-Intent architecture. This is similar to architectures like MVVM but uses intents to display the data loda state.
More information available in [this article](https://blog.mindorks.com/mvi-architecture-android-tutorial-for-beginners-step-by-step-guide).

# Libraries
[Retrofit](https://github.com/square/retrofit) for REST calls

[Dagger/Hilt](https://github.com/google/dagger/tree/master/java/dagger/hilt) for dependency injection

[Room](https://developer.android.com/training/data-storage/room) for caching data locally

[Picasso](https://github.com/square/picasso) for image-loading via API calls

