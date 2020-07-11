<template>
<!-- Dashboard Layout -->
    <v-app id="landing-page">

        <!-- Sidebar 
        si agrego v-model="drawer" a las props me permite abrir y cerrar el sidebar con el boton del navbar 
        app = le indica a la app que este componente es parte del layout de la aplicacion
        clipped = es para que el sidebar este debajo del navbar-->
        <v-navigation-drawer id="side-bar"
        app
        clipped
        expand-on-hover
        fixed
        >
            <!-- Elementos del Sidebar:
            dense = Reduce la altura máxima de los elementos de la lista
            -->
            <v-list v-for="(item,index) in ItemsSideBar" :key="index" dense>
                <!-- Si no es una subcategoria -->
                <v-list-item v-if="!item.subItems" :to=item.route link >
                    <v-list-item-action>
                        <v-icon medium>{{ item.icon }}</v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                        <v-list-item-title>{{ item.title }}</v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
                <!-- Si es una subcategoria -->
                <v-list-group v-else :key="item.title" no-action>
                     <!-- Se crea botón con slot los subitems -->
                    <template v-slot:activator>
                        <v-list-item-action>
                            <v-icon medium>{{ item.icon }}</v-icon>
                        </v-list-item-action>
                        <v-list-item-content>
                            <v-list-item-title>{{ item.title }}</v-list-item-title>
                        </v-list-item-content>
                    </template>
                    <v-list-item v-for="(subItem,i) in item.subItems" :key="i" :to= "subItem.route" > 
                        <v-list-item-action>
                            <v-icon medium>{{ subItem.icon }}</v-icon>
                        </v-list-item-action>
                        <v-list-item-content>
                            <v-list-item-title>{{ subItem.title }}</v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                </v-list-group>
            </v-list>
        </v-navigation-drawer>

        <!-- Navbar:
        app = le indica a la app que este componente es parte del layout de la aplicacion
        clipped-left = indica que el sidebar que esta a la izquierda estará debajo del navbar -->
        <v-app-bar id="nav-bar"
        app
        clipped-left
        >
            <!-- Elementos del navbar -->
            <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
            <v-toolbar-title>Application name</v-toolbar-title>
        </v-app-bar>

        <v-main>
            <router-view></router-view>
        </v-main>


        <!-- Footer -->
        <v-footer app id="footer">
            <span>&copy; Created by Sebastian Orellana & Franco Tapia • {{ new Date().getFullYear() }}</span>
        </v-footer>
    </v-app>
</template>


<script>
  export default {
    props: {
      source: String,
    },
    data: () => ({
        drawer: false,
        ItemsSideBar: [
            {title: "Home",
            route: '/',
            icon: 'home',
            },
          
            {title: "Trending Tweets",
            route: '/trendingtweets',
            icon: 'trending_up',
           },

            {title: 'Politicians',
            icon:'how_to_reg',
            subItems: [
                {title: 'Approval Graph',
                 route: '/chartsApproval',
                 icon:  'show_chart'
                },
                {title: 'Ranking',
                 route: '/rankings',
                 icon:  'bar_chart'
                },

            ] 
            }
      ]
    }),
    created () {
      this.$vuetify.theme.dark = true
    },
  }
</script>

<style scoped>
    #landing-page {
        background: rgb(36,0,71);  
        background: linear-gradient(90deg, rgba(36,0,71,1) 0%, rgba(59,2,115,1) 28%, rgba(140,3,104,1) 52%, rgba(166,3,93,1) 75%, rgba(217,4,82,1) 100%); 
    }

  #side-bar{
        background: rgb(36,0,71);
        background: linear-gradient(90deg, rgba(36,0,71,0.7315125879453344) 0%, rgba(59,2,115,0.7287114674971551) 100%); 
  }
    #nav-bar{
        background: rgb(14,0,27);
        background: linear-gradient(90deg, rgba(14,0,27,0.7) 0%, rgba(14,0,27,0.7035013834635417) 100%);
    }
    #footer{
        background: rgb(14,0,27);
        background: linear-gradient(90deg, rgba(14,0,27,0.7) 0%, rgba(14,0,27,0.7035013834635417) 100%);
    }
</style>