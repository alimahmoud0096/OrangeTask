# OrangeTask App

A modern books App , following **Clean Architecture** principles ,The app fetches books data from the **google books API**.


https://github.com/user-attachments/assets/d67f478f-1375-4499-827e-b0cee2f02cdc



## 🌟 Features

- **Clean Architecture** with separation of concerns (presentation, Domain, Data layers).
- ** using **ViewModel & Flow**.
- **Dependency Injection** with **Hilt**.
- **dataBinding.

## core features
1. Home Screen (Book List)
   - Fetch and display a list of books.
   - Show each book with the following details in a RecyclerView:
   - Title
   - Author
   - Thumbnail image
2. Book Details Screen
   - When a user selects a book, navigate to a details screen.
   - Display detailed information about the selected book:
   - Title
   - Author(s)
   - Description
   - Published Date
   - Larger thumbnail
3. Search Functionality
   - Include a search bar on the Home Screen to search for books by title or author.

## 🔧 Setup Instructions

1. **Clone the repository**:
   ```sh
   git clone https://github.com/alimahmoud0096/OrangeTask.git
   cd OrangeTask
   ```

2. **Add your OrangeTask API Key**:
   - Create a `local.properties` file in the root Android project (if not exists).
   - Add the following line with your API key:
     ```properties
     API_KEY=your_books_api_key
     ```

3. **Sync the project** in Android Studio.

4. **Run the app**:
   ```sh
   ./gradlew installDebug
   ```

## 🚀 Technologies Used

- **Kotlin** – Primary programming language
- **Hilt** – Dependency Injection
- **Retrofit** – Network API calls
- **Coroutines & Flow** – Async operations
- **Navigation Component** – Navigation between screens
- **dataBinding
- 
## ⚠️ Important Notes
- You **must** add `API_KEY="your_books_api_key"` in `local.properties`, or the app will fail to fetch books data.
- The API key is **not** included in the project for security reasons.

