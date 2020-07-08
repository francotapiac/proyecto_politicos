<template>
  <v-row>
    <!-- Componente Timeline
    align-top= ubica el componente al borde superior 
    dense= reduce los bordes
    reverse=-->
    <v-timeline
      :dense="dense"
    >

      <!-- Elementos del Timeline 
        :fill-dot="fillDot"
        :hide-dot="hideDot"-->
      <v-timeline-item
        v-for="tweet in listoftweets"
        :key="tweet.id"
        
      >
        <!-- Imagen de perfil del usuario (Avatar) -->
        <template v-slot:icon>
          <v-avatar v-if="avatar">
            <a v-bind:href= tweet.perfilUser ><img :src= tweet.profileImage ></a>
          </v-avatar>
        </template>

        <!-- Card que contiene el tweet y sus elementos (fecha, retweets, likes) -->
        <v-card 
        id="card-tweet" 
        class="elevation-2">
          
          <!-- Card-Title: Contiene el nombre de usuario y el nombre real de la persona -->
          <v-card-title class="headline">
            <v-row no-gutters>
              {{ tweet.realName }}
              <v-divider class="mx-4" vertical></v-divider>
              <a style="font-size:17px;" v-bind:href= tweet.perfilUser >@{{ tweet.userName }}</a>
            </v-row>
          </v-card-title>

          <!-- Card-Text-1: Contiene el contenido del tweet -->
          <v-card-text style="font-size:16px;">
              {{ tweet.text }}
          </v-card-text>
          <v-divider class="my-1"></v-divider>
          
          <!-- Card-Text-2: Contiene los elementos del tweet -->
          <v-card-text>
            <v-row dense style="margin-top:-10px">
              <v-col cols="10">
                <v-icon medium>today</v-icon>
                <span> {{ tweet.createdAt }}</span>
                <v-divider class="mx-4" vertical></v-divider>
                
                <v-icon medium>repeat</v-icon>
                <span> Retweets: {{ tweet.retweetCount }}</span>
                <v-divider class="mx-4" vertical></v-divider>
            
                <v-icon medium>favorite</v-icon>
                <span class="mr-16"> Likes: {{ tweet.favoriteCount }}</span>
              </v-col>
            
              <v-col cols="2">
                <span style="margin-left:30px">
                  <v-btn v-if="listoftweets" small text :href= tweet.tweetURL > <v-icon medium>read_more</v-icon> Ver más</v-btn>
                  <v-btn v-else> loading...</v-btn>
                </span>
              </v-col>
            </v-row>
          </v-card-text>
        </v-card>
      </v-timeline-item>
    </v-timeline>
  </v-row>
</template>

<script>
export default {
  name: 'Tweets',
  props: [
    "listoftweets",
    "numberoftweets",
  ],
  
    data: () => ({
      avatar: true,
      dense: true,
    }),

}
</script>

<style scoped>
  #card-tweet{
    background: rgb(14,0,27);
    background: linear-gradient(90deg, rgba(14,0,27,0.7) 0%, rgba(14,0,27,0.7035013834635417) 100%);
  }
</style>>
