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

    <b-row class="mb-2">
      <b-col sm="3" class="text-sm-right"><b>ISBN:</b></b-col>
      <b-col>{{ book.isbn }}</b-col>
    </b-row>

    <b-row v-if="book.borrowedBy == null" class="borrow-wrapper">
      <b-col class="text-sm-left">
        <div class="borrow-contents-wrapper">
          <h5>Borrow book</h5>

          <b-form-group
                  id="fieldset-horizontal"
                  label-cols-sm="4"
                  label-cols-lg="3"
                  description="Select person to borrow book."
                  label="Borrower"
                  label-for="input-horizontal"
          >
            <b-form-select v-model="selectedPerson" :options="getPeopleOptions()">
              <template v-slot:first>
                <option :value="null" disabled>Please select person to borrow book</option>
              </template>
            </b-form-select>
          </b-form-group>

          <div class="button-wrapper">
            <b-button variant="success" @click="onBorrow(book)" :disabled="this.selectedPerson === null">Borrow book</b-button>
          </div>
        </div>
      </b-col>
    </b-row>

    <b-row>
      <b-col>
        <div class="button-wrapper">
          <b-button v-if="book.borrowedBy != null" variant="danger" @click="onReturn(book)">Return book</b-button>
          <b-button variant="primary"  @click="onClose">Close</b-button>
        </div>
      </b-col>
    </b-row>

  </b-card>
</template>

<script>
    export default {
        props: {
            book: {
                required: true,
            },
            people: {
                required: true
            }
        },
        data() {
            return {
                selectedPerson: null
            }
        },
        methods: {
            getPeopleOptions() {
                 return this.people.map(person => {
                    return {
                        text: `${person.firstName} ${person.lastName}`,
                        value: person
                    }
                })
            },
            getCardHeader() {
                return `${this.book.title}`;
            },
            onClose() {
                this.selectedPerson = null;
                this.$emit("onCloseCard");
            },
            onReturn(book) {
                this.selectedPerson = null;
                this.$emit("onReturn", book);
            },
            onBorrow(book) {
                if (this.selectedPerson) {
                    this.$emit("onBorrow", {
                        book: book,
                        person: this.selectedPerson
                    });

                    this.selectedPerson = null;
                }
            }
        }
    };
</script>

<style scoped lang="scss">

  .borrow-wrapper {
    margin: 5px 5px 15px 5px;
    padding: 5px;
    border: 1px solid lightgray;
  }

  .borrow-contents-wrapper {
    display: flex;
    flex-direction: column;
  }

  .button-wrapper {
    display: flex;
    justify-content: flex-end;

    button {
      margin-left: 5px;
    }
  }

</style>
