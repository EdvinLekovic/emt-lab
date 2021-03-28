import './App.css';
import React,{Component} from "react";
import {BrowserRouter as Router, Redirect, Route} from 'react-router-dom'
import Authors from "../Authors/authors";
import Countries from "../Countries/countries";
import Header from "../Header/header";
import Books from "../Books/BookList/books";
import BookAdd from "../Books/BookAdd/bookAdd";
import BookEdit from "../Books/BookEdit/bookEdit";
import EBookService from "../../repository/EBookRepository";

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            books: [],
            authors: [],
            countries: [],
            categories: [],
            selectedBook: {}
        }
    }

    render() {
        return (
            <Router>
                <Header/>
                <main>
                    <div>
                        <Route path={"/authors"} exact render={
                            () => <Authors authors={this.state.authors}/>}
                        />
                        <Route path={"/countries"} exact render={
                            () => <Countries countries={this.state.countries}/>}
                        />
                        <Route path={"/books/edit/:id"} exact render={
                            () => <BookEdit onEditBook={this.editBook}
                                            authors={this.state.authors}
                                            categories={this.state.categories}
                                            book={this.state.selectedBook}/>}
                        />
                        <Route path={"/books/add"} exact render={
                            () => <BookAdd onAddBook={this.addBook}
                                           authors={this.state.authors}
                                           categories={this.state.categories}/>}
                        />
                        <Route path={"/books"} exact render={
                            () => <Books books={this.state.books}
                                         onDeleteBook={this.deleteBook}
                                         onEdit={this.getBook}
                                         onTake={this.takeBook}
                            />}
                        />
                        <Redirect to={"/books"}/>
                    </div>
                </main>
            </Router>
        )
    }

    loadBooks = () => {
        EBookService.fetchBooks()
            .then((data) => {
                this.setState({
                    books: data.data
                })
            });

    };

    loadAuthors = () => {
        EBookService.fetchAuthors()
            .then((data) => {
                this.setState({
                    authors: data.data
                })
            });
    };

    loadCountries = () => {
        EBookService.fetchCountries()
            .then((data) => {
                this.setState({
                    countries: data.data
                })
            });
    };


    addBook = (name, category, authorId, availableCopies) => {
        EBookService.addBook(name, category, authorId, availableCopies)
            .then(() => {
                this.loadBooks();
            });
    }

    deleteBook = (id) => {
        EBookService.deleteBook(id)
            .then(() => {
                this.loadBooks();
            });
    }

    editBook = (id, name, category, authorId, availableCopies) => {
        EBookService.editBook(id, name, category, authorId, availableCopies)
            .then(() => {
                this.loadBooks();
            });
    }


    getBook = (id) => {
        EBookService.getBook(id)
            .then((data) => {
                this.setState({
                    selectedBook: data.data
                })
            });
    }

    takeBook = (id) => {
        EBookService.takeBook(id)
            .then(() => {
                this.loadBooks();
            });
    }

    loadCategories = () => {
        EBookService.fetchBookCategories()
            .then((data) => {
                this.setState({
                    categories: data.data
                })
            });
    }


    componentDidMount() {
        this.loadAuthors();
        this.loadCategories();
        this.loadCountries();
        this.loadBooks();
    }


}

export default App;
