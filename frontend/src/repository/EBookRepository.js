import instance from "../custom-axios/axios";
import axios from "../custom-axios/axios";

const EBookService = {
    fetchBooks : () => {
    return axios.get("/books");
    },
    fetchAuthors: () => {
        return axios.get("/authors");
    },
    fetchCountries: () => {
        return axios.get("/countries");
    },
    fetchBookCategories : () => {
      return axios.get("/books/categories");
    },
    addBook : (name,category,authorId,availableCopies) => {
        return axios.post("/books/add", {
            "name" : name,
            "category" : category,
            "authorId" : authorId,
            "availableCopies" : availableCopies
        })
    },
    editBook : (id,name,category,authorId,availableCopies) => {
        return axios.put(`/books/edit/${id}`,{
            "name" : name,
            "category" : category,
            "authorId" : authorId,
            "availableCopies" : availableCopies
        });
    },
    deleteBook : (id) => {
        return axios.delete(`/books/delete/${id}`);
    },
    getBook : (id) => {
        return axios.get(`/books/${id}`);
    },
    takeBook : (id) => {
        return axios.put(`/books/take/${id}`);
    }
};



export default EBookService;