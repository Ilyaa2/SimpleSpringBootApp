<template>
    <div style="position: relative; width: 300px;">
        <message-form :messages="messages" :messageAttr="message" />
        <message-row v-for="message in messages"
                     :key="message.id"
                     :message="message"
                     :editMessage="editMessage"
                     :deleteMessage="deleteMessage"
                     :messages="messages" />
    </div>
</template>

<script>
    import MessageRow from 'components/MessageRow.vue'
    import MessageForm from 'components/MessageForm.vue'

    export default {
        props: ['messages'],
        components:{
            MessageRow,
            MessageForm
        },
        data() {
            return {
                message: null
            }
        },
        created: function(){
            //в then передаем функцию обработки запроса с сервера, которая по готовности исполнится
            //json() - то же возвращает промис
            this.$resource('/message{/id}').get().then(result =>
                result.json().then(data =>
                    //console.log(data)
                    //this.messages = data - так работать будет, но это вредит реактивности, надо использовать функции добавления, в массив к каждому элементу
                    data.forEach(message => this.messages.push(message))
                )

            )
        },
        methods: {
            editMessage: function(message) {
                this.message = message;
            },
            deleteMessage: function(message){
                this.$resource('/message{/id}').remove({id: message.id}).then(result => {
                    if (result.ok) {
                        this.messages.splice(this.messages.indexOf(this.message), 1)
                    }
                })
            }
        }
    }
</script>

<style>

</style>