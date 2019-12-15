import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    apiUrl: process.env.VUE_APP_NATLIB_API_URL,

    filter: null,
    error: null,

    people: [],
    peopleFilterKeys: ['firstName', 'lastName', 'email'],

    books: [],
    booksFilterKeys: ['title', 'isbn', 'author'],
  },
  mutations: {
    setBooks(state, books) {
      state.books = books;
    },
    setPeople(state, people) {
      state.people = people;
    },
    updateFilter(state, filter) {
      state.filter = filter;
    },
    setError(state, error) {
      state.error = error;
    },
  },
  getters: {
    getPeople(state) {
        return state.people;
    },

    filteredPeople(state) {
      return state.people.filter(person => {
        if (state.filter) {
          return state.peopleFilterKeys.some(filterKey => person[filterKey].toLowerCase().includes(state.filter))
        }

        return true;
      })
    },
    filteredBooks(state) {
      return state.books.filter(person => {
        if (state.filter) {
          return state.booksFilterKeys.some(filterKey => person[filterKey].toLowerCase().includes(state.filter))
        }

        return true;
      })
    }
  },
  actions: {
      async loadPeople({ state, commit }) {
          const url = "people";
          const baseUrl = `${state.apiUrl}`;

          await axios
              .get(url, {
                  baseURL: baseUrl
              })
              .then(response => {
                  commit('setPeople', response.data);
              })
              .catch(error => {
                  commit('setError', error)
              });
      },

      async loadBooks({ state, commit }) {
          const url = "books";
          const baseUrl = `${state.apiUrl}`;

          await axios
              .get(url, {
                  baseURL: baseUrl
              })
              .then(response => {
                  commit('setBooks', response.data);
              })
              .catch(error => {
                  commit('setError', error)
              });
      },

      async loadBorrowedBooks({ state, commit }, person) {
          const url = `books/borrowed/${person.id}`;
          const baseUrl = `${state.apiUrl}`;

          return await axios
              .get(url, {
                  baseURL: baseUrl
              })
              .then(response => {
                  return Promise.resolve(response.data)
              })
              .catch(error => {
                  commit('setError', error)
              });
      },

      async borrowBook({ state, commit }, {book, person}) {
          const url = `books/borrowBook`;
          const baseUrl = `${state.apiUrl}`;

          const borrowBook = {
              bookId: book.id,
              personId: person.id
          }

          return await axios
              .post(url, borrowBook, {
                  baseURL: baseUrl,
              })
              .then(response => {
                  return Promise.resolve(response.data)
              })
              .catch(error => {
                  commit('setError', error)
              });
      },

      async returnBook({ state, commit }, book) {
          const url = `books/returnBook`;
          const baseUrl = `${state.apiUrl}`;

          const payload = {
              bookId: book.id
          }

          return await axios
              .post(url, payload, {
                  baseURL: baseUrl
              })
              .then(response => {
                  return Promise.resolve(response.data)
              })
              .catch(error => {
                  commit('setError', error)
              });
      },

      async addPerson({ state, commit }, person) {
          const url = `people/`;
          const baseUrl = `${state.apiUrl}`;

          return await axios
              .post(url, person, {
                  baseURL: baseUrl
              })
              .then(response => {
                  return Promise.resolve(response.data)
              })
              .catch(error => {
                  commit('setError', error)
              });
      },

      async addBook({ state, commit }, book) {
          const url = `books/`;
          const baseUrl = `${state.apiUrl}`;

          return await axios
              .post(url, book, {
                  baseURL: baseUrl
              })
              .then(response => {
                  return Promise.resolve(response.data)
              })
              .catch(error => {
                  commit('setError', error)
              });
      }
  }

});
