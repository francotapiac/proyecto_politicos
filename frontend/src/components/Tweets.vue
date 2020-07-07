<template>
  <div>
    <v-timeline
      :align-top="alignTop"
      :dense="dense"
      :reverse="reverse"
    >
      <v-timeline-item
        v-for="n in 3"
        :key="n"
        :fill-dot="fillDot"
        :hide-dot="hideDot"
        :icon="icon ? 'mdi-star' : ''"
        :icon-color=" iconColor ? 'deep-orange' : ''"
        :left="left"
        :right="right"
        :small="small"
      >
        <template v-slot:icon>
          <v-avatar v-if="avatar">
            <a v-bind:href= listoftweets[n].perfilUser ><img :src= listoftweets[n].profileImage ></a>
          </v-avatar>
        </template>
        <span slot="opposite">Tus eu perfecto</span>
        <v-card id="card-tweet" class="elevation-2">
          <v-card-title class="headline">
            <v-row no-gutters>
              {{ listoftweets[n].realName }}
              <v-divider class="mx-4" vertical></v-divider>
              <a style="font-size:17px;" v-bind:href= listoftweets[n].perfilUser >@{{ listoftweets[n].userName }}</a>
            </v-row>
          </v-card-title>
            <v-card-text>
              {{ listoftweets[n].text }}
            </v-card-text>
          <v-divider class="my-1"></v-divider>
          <v-card-text>
            <v-icon medium>today</v-icon>
            <span> {{ listoftweets[n].createdAt }}</span>
            <v-divider class="mx-4" vertical></v-divider>
            <v-icon medium>repeat</v-icon>
            <span> Retweets: {{ listoftweets[n].retweetCount }}</span>
            <v-divider class="mx-4" vertical></v-divider>
            <v-icon medium>favorite</v-icon>
            <span class="mr-16"> Likes: {{ listoftweets[n].favoriteCount }}</span>
            <v-layout justify-end>
              <span><v-btn small text> <v-icon medium>read_more</v-icon> Ver más</v-btn></span>
            </v-layout>
          </v-card-text>
        </v-card>
      </v-timeline-item>
    </v-timeline>
  </div>
</template>

<script>
export default {
  name: 'Tweets',
  props: [
    "listoftweets"
  ],
    data: () => ({
      avatar: true,
      dense: true,
      url: '/',
      url2: '/otraparte',
    }),

}
</script>

<style scoped>
  #card-tweet{
    background: rgb(14,0,27);
    background: linear-gradient(90deg, rgba(14,0,27,0.7) 0%, rgba(14,0,27,0.7035013834635417) 100%);
  }
</style>>
