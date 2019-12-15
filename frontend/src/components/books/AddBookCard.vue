<template>
    <b-card header="Add Book">

        <b-container fluid>
            <b-row class="my-1">
                <b-col sm="3">
                    <label for="title-id">Title</label>
                </b-col>
                <b-col sm="9">
                    <b-form-input id="title-id" :state="validateField(book.title)"  v-model="book.title" type="text"></b-form-input>
                </b-col>
            </b-row>
            <b-row class="my-1">
                <b-col sm="3">
                    <label for="author-id">Author</label>
                </b-col>
                <b-col sm="9">
                    <b-form-input id="author-id" :state="validateField(book.author)" v-model="book.author" type="text"></b-form-input>
                </b-col>
            </b-row>
            <b-row class="my-1">
                <b-col sm="3">
                    <label for="isbn-id">ISBN</label>
                </b-col>
                <b-col sm="9">
                    <b-form-input id="isbn-id" :state="validateField(book.isbn)"  v-model="book.isbn" type="text"></b-form-input>
                </b-col>
            </b-row>
            <b-row class="my-1">
                <b-col sm="3"></b-col>
                <b-col sm="9">
                    <b-row class="mb-2">
                        <b-col class="text-sm-left">
                            <b-button href="#" variant="primary" @click="onCloseCard">Cancel</b-button>
                        </b-col>
                        <b-col class="text-sm-right">
                            <b-button href="#" variant="success" :disabled="!canAddBook()" @click="onAddBook">Add</b-button>
                        </b-col>
                    </b-row>
                </b-col>
            </b-row>
        </b-container>
    </b-card>
</template>

<script>
    export default {
        data() {
            return {
                book: {
                    title: null,
                    author: null,
                    isbn: null,
                },
            }
        },
        methods: {
            onCloseCard() {
                this.$emit("onCloseCard");
            },
            onAddBook() {
                this.$emit("onAddBook", this.book);
            },
            validateField(value) {
                return value !== null && value.length > 0;
            },
            canAddBook() {
                return Object.values(this.book).every(bookValue => this.validateField(bookValue));
            }
        }
    };
</script>

<style scoped lang="scss">

</style>
