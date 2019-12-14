<template>
  <div id="app">
    <div>
      <b-navbar toggleable="lg" type="dark" variant="dark">
        <b-navbar-brand href="#">National Library Demo</b-navbar-brand>

        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

        <b-collapse id="nav-collapse" is-nav>
          <b-navbar-nav>
            <b-nav-item href="#" to="/" :active="isActiveLink('people')">People</b-nav-item>
            <b-nav-item href="#" to="/books" :active="isActiveLink('books')">Books</b-nav-item>
          </b-navbar-nav>

          <b-navbar-nav class="ml-auto">
            <b-nav-form>
              <b-form-input size="sm" class="mr-sm-2" v-model="searchFilter" placeholder="Search"></b-form-input>
            </b-nav-form>
          </b-navbar-nav>
        </b-collapse>
      </b-navbar>
    </div>
    <router-view/>
  </div>
</template>

<script>

    import {mapMutations, mapState} from "vuex";

    export default {
        data() {
            return {
                activeLink: 'people'
            }
        },
        methods: {
            ...mapMutations({
                updateFilter: "updateFilter"
            }),
            calculateActiveLink() {
                const path = this.$route.path;
                const link = path.substring(1);

                this.activeLink = link ? link : 'people';
            },
            isActiveLink(tabName) {
                return this.activeLink === tabName;
            },
        },
        watch: {
            "$route"(to) {
                document.title = to.meta.title || 'NatLib Demo'
                this.calculateActiveLink();
            },
            error(error) {
                if (error) {
                    this.$bvModal.msgBoxOk(error.toString(), {
                        title: 'System Error Encountered',
                        size: 'sm',
                        buttonSize: 'sm',
                        okVariant: 'danger',
                        headerClass: 'p-2 border-bottom-0',
                        footerClass: 'p-2 border-top-0',
                        centered: true
                    })
                }
            }
        },
        computed: {
            ...mapState({
                error: state => state.error,
                filter: state => state.filter
            }),
            searchFilter: {
                get() {
                    return this.filter
                },
                set(value) {
                    this.updateFilter(value)
                }
            }
          },
        created() {
            this.calculateActiveLink();
        }
    }
</script>

<style lang="scss">
  @import 'node_modules/bootstrap/scss/bootstrap';
  @import 'node_modules/bootstrap-vue/src/index.scss';

  #app {
    font-family: "Avenir", Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
  }
</style>
