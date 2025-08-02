# About OzusNews

OzusNews is a modern Android news application that delivers the latest headlines and articles across various categories, including Business, Entertainment, Health, Science, Sports, and Technology. With a user-friendly interface inspired by BBC News, OzusNews allows users to browse, bookmark, and share news stories with ease. The app features a horizontally scrollable category selector, visually rich news lists, detailed article views, and seamless bookmarking functionality for a personalized news experience.

OzusNews is a modern Android news application built with Kotlin, Jetpack Compose, Hilt (Dagger), Room, and MVVM architecture. It provides users with the latest news articles, bookmarking capabilities, and a clean, intuitive UI.

## Features
- Browse the latest news articles from various sources
- Bookmark articles for offline reading
- Search and filter news by category
- Modern UI with Jetpack Compose
- Offline caching with Room database
- Dependency injection with Hilt
- MVVM architecture for maintainable code

## Getting Started

### Prerequisites
- Android Studio (latest recommended)
- Android device or emulator (API 24+)
- A valid News API key (see below)

### Setup
1. **Clone the repository:**
   ```sh
   git clone https://github.com/yourusername/OzusNews.git
   cd OzusNews
   ```
2. **Add your News API key:**
   - Open `app/build.gradle.kts` and set your API key in the `buildConfigField` for `NEWS_API_KEY`.
   - Or, add it to your `local.properties` or CI/CD secrets as appropriate.
3. **Build and run the app:**
   - Open the project in Android Studio.
   - Click "Run" or use `./gradlew assembleDebug` to build.

## Project Structure
- `app/` - Main Android application module
- `data/` - Data sources and repository implementations
- `domain/` - Domain models and use cases
- `di/` - Dependency injection modules
- `model/` - Data models
- `presentation/` - UI and ViewModels
- `repository/` - Repository interfaces
- `ui/` - UI components
- `util/` - Utility classes

## Tech Stack
- Kotlin
- Jetpack Compose
- Hilt (Dagger)
- Room
- Retrofit
- MVVM

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Acknowledgements
- [NewsAPI.org](https://newsapi.org/) for news data
- Jetpack Compose and Android Open Source Community