import {shallowMount} from "@vue/test-utils";
import BooksCard from "@/components/books/BooksCard.vue";

describe("BooksCard.vue", () => {
    it("renders title when passed", () => {

        const wrapper = shallowMount(BooksCard, {
            propsData: {
                book: {
                    id: 1,
                    title: "title1",
                    author: "author1",
                    isbn: "isbn"
                },
                people: [{
                    id: 1,
                    firstName: "firstName1",
                    lastName: "lastName1",
                    phone: "04 000 000",
                    email: "yay@foo.bar"
                }]
            }
        });

        expect(wrapper.html()).toContain('<b-card header="title1">')
    });
});
