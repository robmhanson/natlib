<template>
  <generic-table title="Books" addTitle="Add book" :items="filteredBooks" :fields="fields" :showAddCard="showAddCard" @onRowSelected="onRowSelected">
    <template slot="selectedCard">
      <books-card :book="selectedBook" :people="people" @onCloseCard="onCloseCard" @onReturn="onReturn" @onBorrow="onBorrow" />
    </template>
    <template slot="addCard">
      <add-book-card @onCloseCard="showAddCard = false" @onAddBook="onAddBook"/>
    </template>
    <template slot="addCardButton">
      <b-button variant="success" @click="showAddCard = true">Add Book</b-button>
    </template>
  </generic-table>
</template>

<script>
    import { mapActions, mapGetters } from "vuex";
    import GenericTable from "../common/GenericTable";
    import BooksCard from "./BooksCard";
    import AddBookCard from "./AddBookCard";

    export default {
        components: {
            GenericTable,
            BooksCard,
            AddBookCard
        },
        data() {
            return {
                selectedBook: null,
                showAddCard: false,

                fields: [
                    {
                        key: 'title',
                        sortable: true
                    },
                    {
                        key: 'author',
                        sortable: true
                    },
                    {
                        key: 'isbn',
                        label: 'ISBN',
                        sortable: true,
                    },
                ]
            }
        },
        methods: {
            ...mapActions({
                loadBooks: 'loadBooks',
                loadPeople: 'loadPeople',
                returnBook: 'returnBook',
                borrowBook: 'borrowBook',
                addBook: 'addBook'
            }),
            onRowSelected(selectedBook) {
                this.selectedBook = selectedBook;
            },
            onCloseCard() {
                this.selectedBook = null
            },
            onReturn(book) {
                this.returnBook(book)
                .then(() => {
                    this.onCloseCard();
                    this.loadBooks();
                    this.showSuccessMessage(`'${book.title}' returned`);
                });
            },
            onBorrow(borrowBook) {
                this.borrowBook(borrowBook)
                    .then(() => {
                        this.onCloseCard();
                        this.loadBooks();
                        this.showSuccessMessage(`'${borrowBook.book.title}' borrowed`);
                    });
            },
            onAddBook(book) {
                this.addBook(book)
                    .then(() => {
                        this.showAddCard = false;
                        this.loadBooks();
                        this.showSuccessMessage(`${book.title} added`);
                    });
            },
            showSuccessMessage(message) {
                this.$bvModal.msgBoxOk(message, {
                    title: 'Success',
                    size: 'sm',
                    buttonSize: 'sm',
                    okVariant: 'success',
                    headerClass: 'p-2 border-bottom-0',
                    footerClass: 'p-2 border-top-0',
                    centered: true
                });
            }
        },
        computed: {
            ...mapGetters({
                filteredBooks: "filteredBooks",
                people: "getPeople"
            })
        },
        mounted() {
            this.loadBooks();
            this.loadPeople();
        }
    };
</script>

<style scoped lang="scss">
</style>
