<template>
  <div class="generic-page-wrapper">
    <h1>{{ title}}</h1>

    <div class="button-wrapper">
      <slot name="addCardButton" />
    </div>

    <div class="table-wrapper">
      <b-table striped hover bordered fixed :items="items" primary-key="id" :fields="fields" @row-clicked="onRowSelected"></b-table>

      <div v-if="selectedRow" class="table-row-details">
        <slot name="selectedCard" />
      </div>

      <div v-if="showAddCard" class="table-row-details">
        <slot name="addCard" />
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
            showAddCard: {
                required: true,
                type: Boolean
            },
        },
        data() {
            return {
                selectedRow: null,
            }
        },
        methods: {
            onRowSelected(row) {
                this.selectedRow = row;
                this.$emit("onRowSelected", row);
            }
         },
    };
</script>

<style scoped lang="scss">
  .generic-page-wrapper {
    position: relative;
  }

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

  .button-wrapper {
    position: absolute;
    right: 20px;
    top: 10px;
  }
</style>
