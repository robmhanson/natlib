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

        <b-button href="#" variant="primary" @click="onClose">Close</b-button>
    </b-card>
</template>

<script>
    export default {
        props: {
          person: {
              required: true
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
