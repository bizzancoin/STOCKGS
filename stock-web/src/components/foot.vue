<template>
  <div class="footCss">
    <div :class="touch == 1 ? 'footDemos' : 'footDemo'" @click="goRouter('/home', 1)">
      <!-- <div class="homeImgOut" v-show="$store.state.select == '/home'">
        <div class="homeImg">
          <img src="~@/assets/foot/ic_home_tab_def.png" />
        </div>
      </div>
      <div v-show="$store.state.select != '/home'" class="footImgDeft">
        <img src="~@/assets/foot/tab_main_home_default.png" />
      </div>
      <div v-show="$store.state.select != '/home'">{{ $t('hj224') }}</div> -->
      <div class="footImgDeft">
        <img v-show="$store.state.select == '/home'" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAUCAYAAACNiR0NAAAB4UlEQVQ4jcXTTYhNYRgH8P+dhqJugzH2bBRjcsdHsZRGYmNlrdih7CR03Uyaspg7NxvZqJGysVHijllYKFlgwcaSkiTZjNz3+f8fndec6XzNMEN56nR6O+/ze96P5+C/RKvjjUuTvvdPavf9bsL4Dd8tx6wDM+ev+/6/Alsd3xWIxyasM0OdwsMzV31f+v3Ehbmtw2NPc0ZtMazZ9tFaH7qB2GAGUIARCMQ3Iw+FYCOUbho5Pj1Rv5jm9Vdhlye94UDXyhiC2QCpR5SeGwlKm7O5JfDKlO+MmCoxkIKRA0aOUYrjRc+w1fGReWxwCQzzK1sYV4LNtu+QY8aEjcvBjDx89PSn0RzY6viwA09MGFomBpPW94J1D5x634i33Gz7dtQwa8KmFWCwZKz4fDHjwX7UcMeENWaYo7C2iFH6auQtucuMJykNVmDfSa0WcHehD89d8yMAHhQwBrM90xP1l8mcY2c/b6P0yqRVGQxmPP7s9pZ7uUvpBZW2GczepVgS96eG3pr0JovFwu7lPpQ7VD6zUOzTYAwJkGLxLZXBZGXJn1i8gGJUYcYKUHLItWTTxsJknJtijHnVW/Yi9mvV+UjPLIsluaU/5Uev95rSh9wZZiqnUcQofTSzF6WJ/yQA/ATFvmo6VwAFvQAAAABJRU5ErkJggg==" />
        <img v-show="$store.state.select != '/home'" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAUCAYAAACNiR0NAAAAAXNSR0IArs4c6QAAAmhJREFUOE/V1D1oFEEUB/D/251NQAQ/wCgxiIgfRL3Z3VnTWJ0QNKhgpRa2dkJqC7FSsBWsgnUQFWy0SmKs/AjOfsyBjeJHEIWAooiEZD+ejNyF8/DuFGzcanf27W9n3nszhH980Z94vu+HALwsyxb6xfcFwzA8REQzROSWZTmRpunjXmhPUCkVAbDYZ2bOiWi4qqqJJEmeWFRKuc8Y8xJA1fpJV1BKqTzPmwHwZWVlpT44OFgAmAewLc/zY0IISURTzHw1juNLPUGbMyHELICvAOpa60X7QRiGw47jWHQrMz8joqMAprXW57qCQRAEruvOMvM3Zq4nSfKuPWe+7293XXeeiPY0x7uDSim7jDlm/l5VVT1N07e/K4CUckQIYdHdXWcYRVGNmeeIaDnP87ox5k2valrU87xHzLy5KIpxY0xs438WRUp5UAjx0GKrq6tHGo3G6w7MbT6X7eNRFO1oFmqjRbMsS8j3/QOu61psiJnvFUUxaYx5bz8MgmCn4zjXAUwQETGzLdRkHMevmhMZ8jzvBoDTAD6VZTlOSqmUiHYBsLNYV1XVWJIkz2u12qaBgYGMmdcDuElEttfOA8jzPPeNMUtKqVEiegFgGYBtqw9rfRhF0QkA91tgFEUXAVwpimLMLqXZNvsdx0mrqrqWJMnlFsjMZ+M4vr2WQ3vTCSqlbhGRr7Ue7chbwsyLcRyf+lvwLhHt1VrLdlAptUBES1rrk/85GATBcdd1HzDzlK0WgDNEVHZZ8gZmngawhYgu2Fit9Z1fimL3qBDiKYCRtpw1uoBjbTEfy7I83NqmfQ/Yfid05/sf/QhnVUE/hqUAAAAASUVORK5CYII=" />
      </div>
      <div :class="$store.state.select == '/home' ? 'blueFont' : ''">{{ $t('hj224') }}</div>
    </div>
    <div :class="touch == 2 ? 'footDemos' : 'footDemo'" @click="goRouter('/trading-list', 2)">
      <div class="footImgDeft">
        <img v-show="$store.state.select == '/trading-list'" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAUCAYAAACNiR0NAAABeklEQVQ4ja2UMW7CQBBFvy0XdLQ5AonSpucm5B65R0QZlCJ9qtyCVISaE9DCzv8TzdqLwLIVCWLJWntm/fRn5nur13d/AvAm4V6OigQkgN0tB6yLWcTYvkcumYMSSDmlHyOfGwCrDoZrYFJeK0oPlFaNhNktMCPzczKLdVZfW2YfJveIVfV/wcjoJVFfCdsZue/DIl9fo8yBeTJb9mGRq0dgOyP2Y2UejsdilwuYhcIhZckwPyQsx3qGqmrt0oNRjnqoTAdOmzuffVD6Lj1LyaAMuIRZGUq/Z4ejig3yJrm/HFP6Kj2LK4N6MLqjGRrAqaRORWdauLfKy3sAzmHxTTM0TaAqyi56VsqMuOW1VVpgEWuGrWGnIeQyjRc9SznnJ5UFFnubEWs8GnnXDifimho5ycAM1ZTSJGo5h0W+GbHGZ4F1014XWCiT+3oIFmrrv/7NIWv0yzwvvekOxzjPboa5u4fC7X/AYmhGbmsjF0Zu5O63KJP7RtLiFyKiJjeMJJElAAAAAElFTkSuQmCC" />
        <img v-show="$store.state.select != '/trading-list'" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAUCAYAAACNiR0NAAAAAXNSR0IArs4c6QAAANlJREFUOE/tlbGqAjEQRe/AfoXgBwjbJOO3qJ2d32JprY1a+az8kR1SqY2ttWDvlYjKIou+XVO84k0Xwpzc4SY34pzrisgCQEdEBA2KJAHsSA7Fe78FcAQwFZG4UbtIRiEjAK0IvAAYmNm6NqnU4L3vAViJqkbF/RRAEfn5G8A8z9tZlp1DCKfH1HHkxgpV9QBgVhTF+FfAKgVlw2oDqxqSAp1zA5L7EEKI4K8VvgL+gTe/3t5DVd2Q3JjZ/G6CkVya2aRq/RHYJCS+eilVBz6ByeMrecCm/gKu1zQ6Apwn4U4AAAAASUVORK5CYII=" />
      </div>
      <div :class="$store.state.select == '/trading-list' ? 'blueFont' : ''">行情</div>
    </div>
    <div :class="touch == 3 ? 'footDemos' : 'footDemo'" @click="goRouter('/warehouse', 3)">
      <div class="footImgDeft">
        <img v-show="$store.state.select == '/warehouse'" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAUCAYAAACNiR0NAAACaUlEQVQ4jZXUPUxTURQH8PNeGwkfAUUqCQIDwbg4KTFx7OSKyIe66OAoROqigxImBuUj2GiiDg6A0TbqZGIc6k7DwigxBogBo0GNEPruOf9jToWEltdWznT70vu7L+f9z/UopMaf6iEW6oNSN5S6WKgFIIjSGjNlHdObr2vf0y+mYkHx7n3g5DPtZdA4C7VDiViIACIGkci/3wIix7oM1eFH9yKvQ8HRafXrqmlKQDcE5FXASOyVoeqYH87Mzg4HX67DHH8XrK/JY4MHwIhFPKgO9Q8MTBS84YMnav16ZRsOgOXXjpmgSszSl5psTHsjk1pVV0ufWKitDPaL82tpCMNE7BCsbG5tdfo11dRTAVt2rKcFOAPoahgmInZIWyQavRiFUk8FLJ6863+21ly7sxknz8s45ta9mB2QP0i1x9/JWUXM6vlY7VLgXByqq8UYAyTQLn8ntAUYlDaKsd2au9+wBGgcwO8izNrQErWPFvY1rUelKggCUqICzNYWEt/GKSQaR4i8zNXbfzqKzQuD3zqV6COL1O/FRNXitO4HjrIlctbu+X7m0q2NjhDseAhGTmTeGx7Ty9aaMqFdDgIXd85F/EgkUwqzZ1C94g0MrVfFYrElAVrLhPanjZkFuxQmwOp2Ltfpv5xuzgmQKIXthPZwOcz+q0SJxdSpXP5yeDwSTTnmZBhWHI0wDKrJ7MyJlFmR3Ya76vPvjzU3N0L1rAi8/8RUgOTC/Ieb9OOdJWn/Bduf2OgFMGGzWQFbYZHEwtzJdNkb2+pc/2LV0aamXlHttnGyCbDQArrmRLICvN3O5dLWs4KNRPQXWdJudvtB9WEAAAAASUVORK5CYII=" />
        <img v-show="$store.state.select != '/warehouse'" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAUCAYAAACNiR0NAAAAAXNSR0IArs4c6QAAAtFJREFUOE+Vlc9rU0EQx7/zXmNurWfRgjQg2JLm7faqmIt4EOyhFcS/QGy1PwSxf0BF0Fqagn+BF1v8RQ/iRdFrdzcJVCimFETFa9pg0/S9HdmQhtf4InWPM7OftzPznXmEhDM4OHgilUqN+74/yswjAE4RkWXmX0S0HkXRq4ODg9WNjY1G53XqNARBMOZ53hMA/cxcBvAZwLdWXD+AC0SUdTZmntZav4wz4kBPSrkIYIKZ34dhOFcul3VSBtlsVvT09MwT0WVrbcEYMw3Autg2UEq5xMy3iei+UupxEqjTJqW8x8yPABS01lNtoJRyHMALa+2sMWbhOLDDmCAIZlyJrLXjxphVymQy6b6+vq/M/EVrfSUJlslkep29UqnsJPmFEO+I6Hy9Xs9QEAQ3iOi5tVYUi8Vi54WhoaEz6XT6AzPbKIrypVLpR2dMLpfLeZ6nmfkmSSlXAAwopUQ3GIA0AJ+Za92gUkrXwC0SQmwT0Vul1N048PBlDsbM+TAM/VQq5V6aCBVCOIVcc8B9Zp4zxjjtNU8ulzvp+/5662WXlFJbzp7NZs85KIDdWq02srm5uRtrziwRzbuU9xxQa/20E8jMPWEY5svl8rbzSSkHAHwE8LsTKISYbgJdygDeHOroEBpLOdVoNPLMTK45APastXljzM94iVopjzabwsxntdZuZo+cOLTlqCfBnE8IsU5E223ZuNJprd3sdoNG/4C52S42ZdMSdgVASSl1NUm4Usq+arXK3YQtpVwDMFytVjPNWY6N3h1jTOE/R2/S87wlANeVUivt5SCEKBDRLWae0lovHwcqhJggokVmfqa1nnR3uq2vtSiKHpRKpY0k8PDw8KDv+w+JyJVoWSnlNs3R9RUTqFuwC8x8GsA6M3/yPK+5YK21/UR0EcAIEX231s64DRP/6F8b2zldo3p7e8eIKPEXwMyvd3Z2ViuVyn5nBn8ACDOJiPW6SUQAAAAASUVORK5CYII=" />
      </div>
      <div :class="$store.state.select == '/warehouse' ? 'blueFont' : ''">持仓</div>
    </div>
    <div :class="touch == 4 ? 'footDemos' : 'footDemo'" @click="goRouter('/MyList', 4)">
      <div class="footImgDeft">
        <img v-show="$store.state.select == '/MyList'" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAUCAYAAACNiR0NAAABgElEQVQ4ja3Uv0vDQBQH8HdBB5fudnNScXQtjgEnl04iKEKXOrSg4uAigli7FLeC4mKL/4GDg4OL0F3wLygddCgO0tz7vpNLezEt1ZSmgePIwX14P/JCs36U9eoNs8lCtwLKGiLSTCRCBCFiIRL0d9gdRNouNiTGECDEQLsXBIVGNfPoWTAlZpe9e2OtEEyJhQsi2QicARaeReCUGBh4gEjHnf2mPB2226xmtrXmSxdpBE6JNbcOOotiTNGlHYEx7D7QtMqg1iSY8rxnBpYtxvEaxiL7uDpW755HvmbTSsIgsuIwHo1wkGb5sGJKlSPVZcAHpDXAOAkbAmM1Uz1tasVzlOpn813N7DPwysBeEiZiQnAujHC4AYoZtf3Tb7q7WLhe819yb08bkoTpeJdHGwARxUBt5+SrNClm70YRjpsAh+bLnzkG1iGy9B/mahiCf42TiFEQyYejlYC5Gg6aMn424+9JmEu5/9kA7bSYGNOOwEDrgkXTYMxcmPnvn4joByP6Wd+wEBzqAAAAAElFTkSuQmCC" />
        <img v-show="$store.state.select != '/MyList'" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAUCAYAAACNiR0NAAAAAXNSR0IArs4c6QAAAWBJREFUOE/dlD1KQ0EQx2febhJs7Gy0UEIUFJbsbswFTFJo5yG8gq0RL2Cvd7CzkCQHEN7HBp6gINjExlIbYXdHXkhAgjHPmEa33vnNx/8/g7DghxlPKbWPiJeIuDoPn4ievfdHSZJcD4Fa6wEAvADA1TxAADgEgJUoitaGwFqtRkTUjqLodB6g1voEEdthGOI/BQohysVi8cxae2yMGfyqZSnlJmOsBwBLzrndJEmevgVKKTcYY+ej7A+fRdJabyNil4g4EbXiODYjl0wXRSm1joi3iOi89804ju+yICGEKBQKHUT01tqmMSYdJ5vZslJqJwiCDhExAGg55xjn/IaI3q21jX6/fz9R+WzbVKvVLc55N5sVEQWI+AoAe2EYPk56dWaF44B6vV52zvWy9p1zjUyAr4yfG5gFVyqV5VKp5NM0fZu2RT8C5lnFPwhc+PmSUh4EQXCxsAObZ/B5/3wAcLkuJG4EsXcAAAAASUVORK5CYII=" />
      </div>
      <div :class="$store.state.select == '/MyList' ? 'blueFont' : ''">自选</div>
    </div>
    <div :class="touch == 5 ? 'footDemos' : 'footDemo'" @click="goRouter('/user', 5)">
      <div class="footImgDeft">
        <img v-show="$store.state.select == '/user'" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAUCAYAAACNiR0NAAAB9klEQVQ4ja3SP2gUURAG8G/frjkJKIhotFKLFBYHsUhjZSrtRRsrQRQEC4sjsRFEm8AJgv8asRBECRYRBMuAabRRFAtTBa1UsPDQZG/fzDfy9vZgb3N3sdiBK2b23o838yZCKe4/sZnM4wMJaPGjAaIACRFi3QuWSbTb89FPDAlXTjKfHxyGQYhEFdMkWkp8vnLLjm8LRtFIDKq9PHzzYvtE9dX5a38PjQW72X9hUBKk7YmcuzkWNGzFvEBLmBYYRDXAp89e/TUxEgyHKjf76gVNr3gnirc0a5L2rcACOuni+HDZSMpJmoEuGmjzoCimAZzc2NyEc/EJmh0ImBcBzcJtd4wGU3xv7ISpIiraDO0seZEzzsUBWFJyoo+p0tI0HVifgT0MsdC2T6JoVh6gW7TZKGEg+fHFnb0zI2cYwgsWK1iYVaOKqWqoL4595RC3F6KnXuxx5TVRxWj2aPnu/mfbgiHerK5eMMO8qHaqGMkOzVpf1tYuDju7ZYblONf6vcuAOQOOkGYk1zPvV17em/oz6kwOXrre3Z0kyRzNJkUUpT3LbxZqWqppqIXWQ94bzUaaZSvvnx/tRJdvyDHn3Gslp8bNrI8FQPv/6+1hDzX7IaqnnHPuQQ0YcgN46Gg2WwPWr806VcY1YWEDYlcjltdcnRhJ/APnnCCiLmRP6QAAAABJRU5ErkJggg==" />
        <img v-show="$store.state.select != '/user'" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAUCAYAAACNiR0NAAAAAXNSR0IArs4c6QAAAnBJREFUOE+llM9rE0EUx9+byY9Sg6KCiAcRzUHcNtndAcGbLRTUgwpSQYpYQah/gBdBEEHw0quHgmAvKige6kEFUXuRgjC76yY5CCv+OIgKIpEo7G5mnkxIJNY12PSdvvvmfT883rxZhL6wbdvmnPv9uT7dJqK3ALCUJMl8vV7/nFWHWUAiOo+IfxiIqEBEFmNsFgBGlVLHgyB4sRqaCUzTdHcYhqabv0IIMQoAi0Q0RUS27/vv+4vWDDRmy7IKIyMjkogCz/NOrxXIAEB3Tb+167qziHi92WxuiaIo7kEHdiiE2ElEj5RSZ42Bc34TEQ9LKT84jrOPMdZI03RvGIavM4Gu61YQ8VUcx+V6vf5GCJEnorsAcLBrWEbEk1LKdGxsbE+xWIwAoCKlrGUCK5XKtnw+/0kpNRkEwXJvXsVi8Z7RcRxPNxqNxOhqtTqRy+Wepmm6PQzDL5lAkxRChES04nneXK/IXILRPZjRrusuIOIBKWXln5fSLZxBxEWl1JEgCJ5krY5t21Oc84cAcEZKeXsgsNvlDQCY0VpfabfbC7Va7ZvJj4+Pb87lcnOMscta61u+758buNh9h+i67gVEvAQAGwDgY/dsBwD8IKKrnufNAwD9L7BTZ1lWqVAoTDLGdplvrfW7JEmeNRqNVtYoTK6zh+VyeWOpVJpARPOs1hxE9LPVaj2Poug7VqtVh3P+GBG3AkBnJYYI8+P4qpQ6ZGa1YuaCiCeklM0hYGbVNhHRfTNvFEK0lVKngiDoLO+wYdv2NOf8jgGS1vqY7/sPhoUZn+M4RxljSz3gNcbYy/UAtdb7GWMXO8D1gFZ7fwFprSac5VGXcwAAAABJRU5ErkJggg==" />
      </div>
      <div :class="$store.state.select == '/user' ? 'blueFont' : ''">{{ $t('hj227') }}</div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      touch: 0,
    }
  },
  mounted() {

  },
  methods: {
    goRouter(url, index) {
      // if (index == 3 || index == 4) {
      //   if (window.localStorage.getItem('USERTOKEN') == "" || window.localStorage.getItem('USERTOKEN') == null || window.localStorage.getItem('USERTOKEN') == undefined) {
      //     this.$emit('close')
      //     return;
      //   }
      // }
      this.touch = index
      setTimeout(() => {
        this.touch = 0
      }, 500)
      this.$router.push(url)
      if (navigator.vibrate) {
        // 支持
        navigator.vibrate([55]);
      }
    }
  },

}
</script>

<style>
.footCss {
  border-top: 0.01rem solid rgba(192, 192, 192, 0.1);
  position: fixed;
  bottom: 0;
  width: 100%;
  height: 1.3rem;
  display: flex;
  justify-content: space-around;
}

.footDemo {
  width: 20%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  flex-wrap: wrap;
  font-size: 0.32rem;
  color: #999;
}

.footDemos {
  width: 20%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  flex-wrap: wrap;
  font-size: 0.32rem;
}

.footDemos::before {
  content: '';
  width: 0.9rem;
  height: 0.9rem;
  border-radius: 100%;
  position: absolute;
  background-color: rgba(25, 122, 246, 0.14);
  -webkit-animation: footBlueBg 0.5s linear infinite;
  animation: footBlueBg 0.5s linear infinite;
  transition: all 0.5s;
}

@-webkit-keyframes footBlueBg {
  0% {
    -webkit-transform: scale(1.6);
    transform: scale(1.6);
  }

  25% {
    -webkit-transform: scale(1.3);
    transform: scale(1.3);
  }

  50% {
    -webkit-transform: scale(1);
    transform: scale(1);
  }

  75% {
    background-color: rgba(25, 122, 246, 0.1);

  }

  100% {
    background-color: rgba(25, 122, 246, 0);
  }
}

.footImgDeft {
  width: 0.46rem;
  height: 0.46rem;
  margin-bottom: 0.14rem;

}

.footImgDeft img {
  width: 100%;
  height: 100%;
}

.homeImgOut {
  width: 0.9rem;
  height: 0.9rem;
  border-radius: 100%;
  background-color: rgb(25, 122, 246);
}

.homeImg {
  width: 0.9rem;
  height: 0.9rem;
}

.homeImg img {
  width: 100%;
  height: 100%;
}

.blueFont {
  color: #5d7dfb;
}
</style>
