<template>
    <mdb-container class="green-text">
      <div class="deep-purple-skin">
        <mdb-side-nav logo="https://mdbootstrap.com/img/logo/mdb-transaprent-noshadows.png" sideNavClass="sn-bg-4" mask="strong" :OpenedFromOutside.sync="toggleA" hidden waves>
          <ul>
            <li>
              <ul class="social">
              <li><a href="#" class="icons-sm fb-ic"><mdb-icon fab icon="facebook" /></a></li>
              <li><a href="#" class="icons-sm pin-ic"><mdb-icon fab icon="pinterest" /></a></li>
              <li><a href="#" class="icons-sm gplus-ic"><mdb-icon fab icon="google-plus" /></a></li>
              <li><a href="#" class="icons-sm tw-ic"><mdb-icon fab icon="twitter" /></a></li>
              </ul>
            </li>
            <li>
              <form class="search-form" role="search">
                <div class="form-group md-form mt-0 pt-1 ripple-parent" @click="wave">
                  <input type="text" class="form-control" placeholder="Search">
                </div>
              </form>
            </li>
            <li>
              <mdb-side-nav-nav>
                <mdb-side-nav-cat name="Submit blog" icon="chevron-right">
                  <mdb-side-nav-item href="#">Submit listing</mdb-side-nav-item>
                  <mdb-side-nav-item href="#">Registration form</mdb-side-nav-item>
                </mdb-side-nav-cat>
                <mdb-side-nav-cat name="Instruction" icon="hand-pointer" far>
                  <mdb-side-nav-item href="#">For bloggers</mdb-side-nav-item>
                  <mdb-side-nav-item href="#">For authors</mdb-side-nav-item>
                </mdb-side-nav-cat>
                <mdb-side-nav-cat name="About" icon="eye">
                  <mdb-side-nav-item href="#">Introduction</mdb-side-nav-item>
                  <mdb-side-nav-item href="#">Monthly meetings</mdb-side-nav-item>
                </mdb-side-nav-cat>
                <mdb-side-nav-cat name="Contact me" icon="envelope" far>
                  <mdb-side-nav-item href="#">FAQ</mdb-side-nav-item>
                  <mdb-side-nav-item href="#">Write a message</mdb-side-nav-item>
                </mdb-side-nav-cat>
                <mdb-side-nav-item header icon="envelope" href="#">Write a message</mdb-side-nav-item>
                <mdb-side-nav-item header icon="user" href="#">Profile</mdb-side-nav-item>
              </mdb-side-nav-nav>
            </li>
          </ul>
        </mdb-side-nav>
      </div>
      <div class="text-center icon-div">
        <mdb-icon icon="bars" size="5x" @click.native="toggleLeftSideNav"/>
      </div>
    </mdb-container>
</template>

<style scoped>
  .container {
    height: 750px;
  }
  .icon-div {
    width: 49%;
    display: inline-block;
  }
  .icon-div .fa {
    cursor: pointer;
  }
</style>

<script>
import { mdbSideNav, mdbSideNavNav, mdbSideNavCat, mdbSideNavItem, mdbContainer, mdbIcon, waves }
  from 'mdbvue'
export default {
  name: 'SideNavPage',
  components: {
    mdbSideNav,
    mdbSideNavNav,
    mdbSideNavCat,
    mdbSideNavItem,
    mdbContainer,
    mdbIcon
  },
  data () {
    return {
      toggleA: false
    }
  },
  methods: {
    toggleLeftSideNav () {
      this.toggleA = !this.toggleA
    }
  },
  mixins: [waves]
}
</script>
