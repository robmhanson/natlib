<template>
    <b-card header="Add Person">

        <b-container fluid>
            <b-row class="my-1">
                <b-col sm="3">
                    <label for="first-name-id">First name</label>
                </b-col>
                <b-col sm="9">
                    <b-form-input id="first-name-id" :state="validateField(person.firstName)"  v-model="person.firstName" type="text"></b-form-input>
                </b-col>
            </b-row>
            <b-row class="my-1">
                <b-col sm="3">
                    <label for="last-name-id">Last name</label>
                </b-col>
                <b-col sm="9">
                    <b-form-input id="last-name-id" :state="validateField(person.lastName)" v-model="person.lastName" type="text"></b-form-input>
                </b-col>
            </b-row>
            <b-row class="my-1">
                <b-col sm="3">
                    <label for="email-id">Email</label>
                </b-col>
                <b-col sm="9">
                    <b-form-input id="email-id" :state="validateField(person.email)"  v-model="person.email" type="email"></b-form-input>
                </b-col>
            </b-row>
            <b-row class="my-1">
                <b-col sm="3">
                    <label for="phone-id">Phone</label>
                </b-col>
                <b-col sm="9">
                    <b-form-input id="phone-id" :state="validateField(person.phone)"  v-model="person.phone" type="text"></b-form-input>
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
                            <b-button href="#" variant="success" :disabled="!canAddPerson()" @click="onAddPerson">Add</b-button>
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
                person: {
                    firstName: null,
                    lastName: null,
                    phone: null,
                    email: null,
                },
            }
        },
        methods: {
            onCloseCard() {
                this.$emit("onCloseCard");
            },
            onAddPerson() {
                this.$emit("onAddPerson", this.person);
            },
            validateField(value) {
                return value !== null && value.length > 0;
            },
            canAddPerson() {
                return Object.values(this.person).every(personValue => this.validateField(personValue));
            }
        }
    };
</script>

<style scoped lang="scss">

</style>
