<template>
    <v-app id="landing-page">
        <div class="d-none d-md-flex d-lg-flex d-xl-flex">
            <v-toolbar id="navbar" 
            flat
            dark>
                <v-toolbar-title>
                    Aplication
                </v-toolbar-title>
                <v-spacer></v-spacer>
                <v-toolbar-items v-for="(item,index) in itemsNavbar" :key="index">
                    <v-btn v-if="!item.subItems"
                    flat
                    text
                    :to="item.route">
                        {{item.title}}
                    </v-btn>
                    <v-menu v-else
                    :rounded=0
                    offset-y
                    >
                        <template v-slot:activator="{ attrs, on }">
                            <v-btn
                            text
                            v-bind="attrs"
                            v-on="on"
                            >
                            {{ item.title }}
                            </v-btn>
                        </template>

                        <v-list>
                            <v-list-item
                            v-for="item2 in item.subItems"
                            :key="item2"
                            link
                            :to="item2.route"
                            >
                            <v-list-item-title v-text="item2.title"></v-list-item-title>
                            </v-list-item>
                        </v-list>
                    </v-menu>
                </v-toolbar-items>
            </v-toolbar>
        </div>

        <div class="d-flex d-sm-flex d-md-none">
            <v-navigation-drawer id="side-bar"
            v-model="drawer"
            absolute
            temporary
            dark
            >
            <!-- Elementos del Sidebar:
            dense = Reduce la altura máxima de los elementos de la lista
            -->
            <v-list v-for="(item,index) in itemsNavbar" :key="index" dense>
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
        <v-toolbar id="navbar-small-display"
        flat
        dark
        >
            <!-- Elementos del navbar -->
            <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
            <v-toolbar-title>Application name</v-toolbar-title>
        </v-toolbar>
        </div>
        <router-view></router-view>

    </v-app>
</template>


<script>
  export default {
    props: {
      source: String,
    },
    data: () => ({
        drawer: false,
        itemsNavbar: [
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
    },
  }
</script>

<style scoped>
    #landing-page {
        background-color:#F2F2F2;    
    }

  #side-bar{
        background: rgb(37,55,91);
        background: linear-gradient(90deg, rgba(37,55,91,0.7699813201647847) 0%, rgba(37,55,91,0.7671801997166054) 100%);
}
    #navbar{
        background-color: #25375B;
    }
    #footer{
        background: rgb(14,0,27);
        background: linear-gradient(90deg, rgba(14,0,27,0.7) 0%, rgba(14,0,27,0.7035013834635417) 100%);
    }
    #app-bar{
        background-color: #F2F2F2;
    }
    #navbar-small-display{
        background-color: #25375B;
    }
</style>