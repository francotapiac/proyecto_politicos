<template>
  <v-row no-gutters>
    <v-col md=8 lg=8 sm=12 xs=12>
      <!-- Componente Timeline
      align-top= ubica el componente al borde superior 
      dense= reduce los bordes
      reverse=-->
      <v-timeline
        :dense="dense"
        style="margin-left:-25px;"
      >

        <!-- Elementos del Timeline 
          :fill-dot="fillDot"
          :hide-dot="hideDot"-->
        <v-timeline-item
          v-for="n in numberoftweets"
          :key="n"
          
        >
          <!-- Imagen de perfil del usuario (Avatar) -->
          <template v-slot:icon>
            <v-avatar v-if="avatar">
              <a v-bind:href= listoftweets[n].perfilUser ><img :src= listoftweets[n].profileImage ></a>
            </v-avatar>
          </template>

          <!-- Card que contiene el tweet y sus elementos (fecha, retweets, likes) -->
          <v-card 
          id="card-tweet" 
          class="elevation-2">
            
            <!-- Card-Title: Contiene el nombre de usuario y el nombre real de la persona -->
            <v-card-title class="headline">
              <v-row no-gutters>
                <v-col cols=11 style="font-weight:bold;color:#292F33;">
                  {{ listoftweets[n].realName }}
                </v-col>
                <v-spacer></v-spacer>
                <v-icon large color="#198CD3">group</v-icon>
                <v-col cols=12 style="margin-top:-11px;">
                  <a style="font-size:14px;color:grey;" 
                  v-bind:href= listoftweets[n].perfilUser >
                @{{ listoftweets[n].userName }}</a>
                </v-col>
              </v-row>
            </v-card-title>

            <!-- Card-Text-1: Contiene el contenido del tweet -->
            <v-card-text style="font-size:16px;color:#292F33;">
                {{ listoftweets[n].text }}
            </v-card-text>
            <v-divider class="my-1"></v-divider>
            
            <!-- Card-Text-2: Contiene los elementos del tweet -->
            <v-card-text>
              <v-row
              no-gutters
                align="center"
                justify="start"
              >
                <v-icon class="mr-1" medium color="#198CD3">today</v-icon>
                <span class="mr-2"> {{ listoftweets[n].createdAt }}</span>
                <span class="mr-1" style="font-weight:bold;font-size:20px;">•</span>
                <v-icon class="mr-1" medium color="#198CD3">repeat</v-icon>
                <span class="mr-2"> Retweets: {{ listoftweets[n].retweetCount }}</span>
                <span class="mr-1" style="font-weight:bold;font-size:20px;">•</span>
                <v-icon class="mr-1" medium color="#198CD3">favorite</v-icon>
                <span class="mr-2"> Likes: {{ listoftweets[n].favoriteCount }}</span>
                <v-spacer></v-spacer>
                <v-btn v-if="listoftweets" small color="primary" :href= listoftweets[n].tweetURL >
                      <v-icon medium>read_more</v-icon>
                        Ver más
                    </v-btn>
              </v-row>
              <v-row>
                
              </v-row>
            </v-card-text>
          </v-card>
        </v-timeline-item>
      </v-timeline>
    </v-col>
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
    
  }
</style>>
