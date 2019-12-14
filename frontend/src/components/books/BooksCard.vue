<template>
    <b-card v-if="book" :header="getCardHeader()">

        <b-row class="mb-2">
            <b-col sm="3" class="text-sm-right"><b>Author:</b></b-col>
            <b-col>{{ book.author }}</b-col>
        </b-row>

        <b-row class="mb-2">
            <b-col sm="3" class="text-sm-right"><b>ISBN:</b></b-col>
            <b-col>{{ book.isbn }}</b-col>
        </b-row>

        <b-button href="#" variant="primary" @click="onClose">Close</b-button>
    </b-card>
</template>

<script>
    export default {
        props: {
          book: {
              required: true
          }
        },
        methods: {
            getCardHeader() {
                return `${this.book.title}`;
            },
            onClose() {
                this.$emit("onCloseCard");
            }
        }
    };
</script>

<style scoped lang="scss">

</style>
