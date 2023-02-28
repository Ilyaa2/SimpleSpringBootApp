<template>
    <div>
        <h1>Главная страница</h1>
        <message-list :messages="messages"/>
        <router-view></router-view>
    </div>
</template>

<script>
    import MessageList from 'components/MessageList.vue'

    export default {
        name: "App",
        components: {
            MessageList
        },
        data() {
            return {
                messages: [],
            }
        }
    }
</script>

<style>

</style>