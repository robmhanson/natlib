<template>
  <generic-table title="People" :items="filteredPeople" :fields="fields" showDetails @onRowSelected="onRowSelected">
    <template slot="selectedCard">
      <people-card :person="selectedPerson" @onCloseCard="selectedPerson = null"/>
    </template>
  </generic-table>
</template>

<script>
    import {mapActions, mapGetters} from "vuex";
    import GenericTable from "../common/GenericTable";
    import PeopleCard from "./PeopleCard";

    export default {
        components: {
            GenericTable,
            PeopleCard
        },
        data() {
            return {
                selectedPerson: null,
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
                ]
            }
        },
        methods: {
            ...mapActions({
                loadPeople: 'loadPeople'
            }),
            onRowSelected(selectedPerson) {
                this.selectedPerson = selectedPerson;
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
