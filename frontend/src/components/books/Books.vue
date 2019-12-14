<template>
  <generic-table title="Books" :items="filteredBooks" :fields="fields" showDetails @onRowSelected="onRowSelected">
    <template slot="selectedCard">
      <books-card :book="selectedBook" @onCloseCard="selectedBook = null"/>
    </template>
  </generic-table>
</template>

<script>
    import {mapActions, mapGetters} from "vuex";
    import GenericTable from "../common/GenericTable";
    import BooksCard from "./BooksCard";

    export default {
        components: {
            GenericTable,
            BooksCard
        },
        data() {
            return {
                selectedBook: null,
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
                loadBooks: 'loadBooks'
            }),
            onRowSelected(selectedBook) {
                this.selectedBook = selectedBook;
            }
        },
        computed: {
            ...mapGetters({
                filteredBooks: "filteredBooks"
            })
        },
        mounted() {
            this.loadBooks();
        }
    };
</script>

<style scoped lang="scss">
</style>
