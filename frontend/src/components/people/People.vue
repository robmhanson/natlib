<template>
  <generic-table title="People" :items="filteredPeople" :fields="fields" :showAddCard="showAddCard"
                 @onRowSelected="onRowSelected">
    <template slot="selectedCard">
      <people-card :person="selectedPerson" @onCloseCard="selectedPerson = null"/>
    </template>
    <template slot="addCard">
      <add-person-card @onCloseCard="showAddCard = false" @onAddPerson="onAddPerson"/>
    </template>
    <template slot="addCardButton">
      <b-button variant="success" @click="showAddCard = true">Add Person</b-button>
    </template>
  </generic-table>
</template>

<script>
    import {mapActions, mapGetters} from "vuex";
    import GenericTable from "../common/GenericTable";
    import PeopleCard from "./PeopleCard";
    import AddPersonCard from "./AddPersonCard";

    export default {
        components: {
            GenericTable,
            PeopleCard,
            AddPersonCard
        },
        data() {
            return {
                selectedPerson: null,
                showAddCard: false,

                fields: [
                    {
                        key: 'firstName',
                        sortable: true
                    },
                    {
                        key: 'lastName',
                        sortable: true
                    },
                    {
                        key: 'email',
                        sortable: true,
                    },
                    {
                        key: 'phone',
                        sortable: true,
                    },
                ],
            }
        },
        methods: {
            ...mapActions({
                loadPeople: 'loadPeople',
                loadBorrowedBooks: 'loadBorrowedBooks',
                addPerson: 'addPerson'
            }),
            onRowSelected(selectedPerson) {
                this.loadBorrowedBooks(selectedPerson)
                    .then(books => {

                        if (books) {
                            this.selectedPerson = {
                                ...selectedPerson,
                                books: books,
                            };
                        }
                    })
            },
            onAddPerson(person) {
                this.addPerson(person)
                    .then(() => {
                        this.showAddCard = false;
                        this.loadPeople();
                        this.showSuccessMessage(`${person.firstName} ${person.lastName} added`)
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
                filteredPeople: "filteredPeople"
            })
        },
        mounted() {
            this.loadPeople();
        }
    };
</script>

<style scoped lang="scss">
</style>
