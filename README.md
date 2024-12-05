# App Demo

<div style="display: flex; justify-content: space-around;">
  <img src="https://github.com/user-attachments/assets/a2cfd4e8-9858-4bd9-b088-c60222206b83" alt="GIF 1" width="200" height="400">
  <img src="https://github.com/user-attachments/assets/a792a509-9158-4fb3-b401-9c5e64ce7552" alt="GIF 2" width="200" height="400">
</div>

# PersonaGen - Kotlin-Edition
PersonaGen is an Android app that generates random user profiles based on nationality and gender using the RandomUser.me API. The app is rebuilt in Kotlin using MVVM architecture, Kotlin Coroutines for asynchronous programming, Koin for dependency injection, and Room for local data storage. It provides basic functionality for profile generation, offline access, and network status handling.

# Features
- Random User Generation: Generate users by choosing nationality and gender.
- Data Persistence: All generated user data is stored locally to avoid data loss.
- Offline Support: Access previously generated users when there's no internet connection.
- Network Connection Alert: Displays a dialog if there's no network, prompting the user to check their connection.
- Modern UI: The app uses Material Design principles for a clean and user-friendly interface.
- Clean Architecture: Built with MVVM architecture for better separation of concerns and testability.

# Tech Stack
- Programming Language: Kotlin
- Architecture: MVVM (Model-View-ViewModel) for separation of concerns and scalable structure
- Networking: Retrofit for API interactions
- Asynchronous Programming: Kotlin Coroutines
- Dependency Injection: Koin
- Database: Room for offline data storage
- UI/UX: Material Design components for a modern and intuitive interface
