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
            commit('setPeople', response.data.content);
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
            commit('setBooks', response.data.content);
          })
          .catch(error => {
            commit('setError', error)
          });
    }
  }
});
