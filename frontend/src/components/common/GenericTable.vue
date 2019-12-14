<template>
  <div>
    <h1>{{ title}}</h1>

    <div class="table-wrapper">
      <b-table striped hover sticky-header bordered :items="items" primary-key="id" :fields="fields" :selectable="showDetails" @row-clicked="onRowSelected"></b-table>

      <div v-if="selectedRow" class="table-row-details">
        <slot name="selectedCard" :person="selectedRow" />
      </div>
    </div>
  </div>
</template>

<script>
    export default {
        props: {
            title: {
                required: true,
                type: String
            },
            items: {
                required: true,
                type: Array
            },
            fields: {
                required: true,
                type: Array
            },
            showDetails: {
                type: Boolean
            }
        },
        data() {
            return {
                selectedRow: null
            }
        },
        methods: {
            onRowSelected(row) {
                if (this.showDetails) {
                    this.selectedRow = row;
                    this.$emit("onRowSelected", row);
                }
            }
        },
    };
</script>

<style scoped lang="scss">
  .table-wrapper {
    padding: 20px;
    position: relative;
  }

  .table-row-details {
    position: absolute;
    top: 20%;
    left: 35%;
    width: 35%;
    z-index: 2;
  }
</style>
