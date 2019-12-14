import Vue from "vue";
import Router from "vue-router";
import People from "./components/people/People.vue";

Vue.use(Router);

export default new Router({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/",
      name: "people",
      component: People,
      meta: { title: "People - NatLib Demo" }
    },
    {
      path: "/books",
      name: "books",
      meta: { title: "Books - NatLib Demo" },
      component: () =>
        import(/* webpackChunkName: "book" */ "./components/books/Books.vue")
    }
  ]
});
