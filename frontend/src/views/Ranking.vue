<template>
    <v-container>
        <v-jumbotron>
            <v-container fill-height>
                <v-row align-center>
                    <v-col cols="9">
                        <h3 class="display-3">Ranking</h3>
                        <span class="subheading">Aqui se coloca una pequeña descripcion de la sección</span>
                    </v-col>
                </v-row>
                <v-row>
                    <v-col>
                        <v-divider class="my-3"></v-divider>
                        <v-card>
                            <rankingChart/>
                        </v-card>
                    </v-col>
                </v-row>
            </v-container>
        </v-jumbotron>
    </v-container>
</template>

<script>
import RankingChart from '../components/politicals/rankingChart.vue';
export default {
    components: {
      RankingChart,
    },
}
</script>

<style scoped>

</style>