<template>
    <b-card v-if="person" :header="getCardHeader()">

        <b-row class="mb-2">
            <b-col sm="3" class="text-sm-right"><b>Email:</b></b-col>
            <b-col>{{ person.email }}</b-col>
        </b-row>

        <b-row class="mb-2">
            <b-col sm="3" class="text-sm-right"><b>Phone:</b></b-col>
            <b-col>{{ person.phone }}</b-col>
        </b-row>

        <b-row class="mb-2">
            <b-col class="text-sm-center">
                <b-table v-if="person.books.length > 0" striped hover bordered :items="person.books" :fields="bookFields"></b-table>
                <template v-else>
                    {{ person.firstName }} has not borrowed any books.
                </template>
            </b-col>
        </b-row>

        <b-row class="mb-2">
            <b-col class="text-sm-right">
                <b-button href="#" variant="primary" @click="onClose">Close</b-button>
            </b-col>
        </b-row>

    </b-card>
</template>

<script>
    export default {
        props: {
          person: {
              required: true
          }
        },
        data() {
            return {
                bookFields: [
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
            getCardHeader() {
                return `${this.person.firstName} ${this.person.lastName}`;
            },
            onClose() {
                this.$emit("onCloseCard");
            }
        }
    };
</script>

<style scoped lang="scss">

</style>
