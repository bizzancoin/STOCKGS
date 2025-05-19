<template>
  <div class="user_page">
    <!-- <div class="content">
                            <div class="top_icon">
                              <div class="right_icon">
                                <div @click="goToTopUp()">
                                  <img src="@/assets/img/qianbao.png" alt />
                                </div>
                                <div @click="goOnline" style="justify-content: flex-end;">
                                  <img style="width: 0.5rem;height: 0.5rem;" src="@/assets/img/kefu.png" alt />
                                </div>
                              </div>
                            </div>
                            <div class="users" @click="goWall()">
                              <div class="left_tou">
                                <div class="left_tx">
                                  <div>
                                    <img src="@/assets/img/eslogo.png" alt />
                                  </div>
                                </div>
                                <div class="right_name">
                                  <span>{{ userInfo.realName ? userInfo.realName : userInfo.phone }}</span>
                                </div>
                              </div>
                              <div class="right_go">
                                <img src="@/assets/img/youjiantou.png" alt />
                              </div>
                            </div>
                            <div class="center_card">
                              <div class="keyon">
                                <span>{{ $t('hj49') }}</span>
                              </div>
                              <div class="num_price" v-if="userInfo.length == 0">
                                ¥0
                              </div>
                              <div class="num_price" v-if="userInfo.length != 0">
                                <p v-if="this.$store.state.settingForm.indexDisplay && !this.$store.state.settingForm.futuresDisplay"
                                  class="account">
                                  ¥{{ $store.state.hide ? '****' : Number($store.state.userInfo.userAmt +
                                      $store.state.userInfo.userIndexAmt).toFixed(2)
                                  }}
                                </p>
                                <p v-else-if="!this.$store.state.settingForm.indexDisplay && this.$store.state.settingForm.futuresDisplay"
                                  class="account">
                                  ¥{{ $store.state.hide ? '****' : Number($store.state.userInfo.userAmt +
                                      $store.state.userInfo.userFuturesAmt).toFixed(2)
                                  }}
                                </p>
                                <p v-else-if="!this.$store.state.settingForm.indexDisplay && !this.$store.state.settingForm.futuresDisplay"
                                  class="account">¥&nbsp;&nbsp;{{ $store.state.hide ? '****' :
                                      Number($store.state.userInfo.userAmt).toFixed(2)
                                  }}</p>
                              </div>
                              <div class="yk es">
                                <div>
                                  <span>{{ $t('hj55') }}</span>
                                </div>
                                <div>
                                  <span>{{ $t('hj141') }}</span>
                                </div>

                                <div>
                                  <span>{{ $t('hj48') }}</span>
                                </div>
                              </div>
                              <div class="yk as">
                                <div>
                                  <span class="orenge">¥ {{ userInfo.length != 0 ? ($store.state.userInfo.allFreezAmt + $store.state.userInfo.djzj).toFixed(2) : 0
                                  }}</span>
                                </div>
                                <div v-if="userInfo.length == 0">
                                  <span v-if="!selectUserFlag">
                                    <span>{{ '¥ 0' }}</span>
                                  </span>
                                  <span v-else>
                                    <span>{{ '¥ 0' }}</span>
                                  </span>
                                </div>
                                <div v-if="userInfo.length != 0">
                                  <span v-if="!selectUserFlag">
                                    <span
                                      :class="$store.state.userInfo.allIndexProfitAndLose > 0 ? ' red' : $store.state.userInfo.allIndexProfitAndLose < 0 ? ' green' : ''">{{
                                          '¥ ' + $store.state.userInfo.allIndexProfitAndLose
                                      }}</span>
                                  </span>
                                  <span v-else>
                                    <span
                                      :class="$store.state.userInfo.allProfitAndLose > 0 ? ' red' : $store.state.userInfo.allProfitAndLose < 0 ? ' green' : ''">{{
                                          '¥ ' + $store.state.userInfo.allProfitAndLose
                                      }}</span>
                                  </span>
                                </div>

                                <div v-if="userInfo.length == 0">
                                  <span class="bzz" v-if="!selectUserFlag">{{ '¥ 0' }}</span>
                                  <span class="bzz" v-else>{{ '¥ 0' }}</span>
                                </div>
                                <div v-if="userInfo.length != 0">
                                  <span class="bzz" v-if="!selectUserFlag">{{ '¥ ' + $store.state.userInfo.enableIndexAmt }}</span>
                                  <span class="bzz" v-else>{{ '¥ ' + $store.state.userInfo.enableAmt }}</span>
                                </div>

                              </div>
                              <div class="btns" @click="handleZh()" :class="selectUserFlag ? '' : 'active'">
                                <span>{{ selectUserFlag ? $t('hj142') : $t('hj143') }}</span>
                              </div>
                            </div>
                            <div class="jy" @click="goToSettings()">
                              <div class="left_gn">
                                <div class="l_icon">
                                  <img src="../../assets/img/xiugaimima.png" alt />
                                </div>
                                <div class="r_title">
                                  <span>{{ $t('hj144') }}</span>
                                </div>
                              </div>
                              <div class="right_gos">
                                <img src="../../assets/img/youjiantou.png" alt />
                              </div>
                            </div>
                            <div class="jy" @click="handleGoToTransfer()">
                              <div class="left_gn">
                                <div class="l_icon">
                                  <img src="../../assets/img/huazhuan2.png" alt />
                                </div>
                                <div class="r_title">
                                  <span>{{ $t('hj145') }}</span>
                                </div>
                              </div>
                              <div class="right_gos">
                                <img src="../../assets/img/youjiantou.png" alt />
                              </div>
                            </div>
                            <div class="jy" @click="handleGoToAuthentication()">
                              <div class="left_gn">
                                <div class="l_icon">
                                  <img src="../../assets/img/shiming.png" alt />
                                </div>
                                <div class="r_title">
                                  <span>{{ $t('hj146') }}</span>
                                </div>
                              </div>
                              <div class="right_gos">
                                <img src="../../assets/img/youjiantou.png" alt />
                              </div>
                            </div>
                            <div class="jy" @click="handleGoToBankCard()">
                              <div class="left_gn">
                                <div class="l_icon">
                                  <img src="../../assets/img/shiming.png" alt />
                                </div>
                                <div class="r_title">
                                  <span>{{ $t('hj147') }}</span>
                                </div>
                              </div>
                              <div class="right_gos">
                                <img src="../../assets/img/youjiantou.png" alt />
                              </div>
                            </div>
                            <div class="jy" @click="handleOutLoginClick()">
                              <div class="left_gn">
                                <div class="l_icon">
                                  <img src="../../assets/img/out2.png" alt />
                                </div>
                                <div class="r_title">
                                  <span>{{ $t('hj148') }}</span>
                                </div>
                              </div>
                              <div class="right_gos">
                                <img src="../../assets/img/youjiantou.png" alt />
                              </div>
                            </div>
                          </div>
                          <van-popup v-model="settingDialog" position="bottom" :style="{ height: '40%' }">
                            <div class="setting_content">
                              <div class="old_password">
                                <div class="left_titles">
                                  <span>{{ $t('hj150') + ':' }}</span>
                                </div>
                                <div class="right_password_input">
                                  <input type="password" v-model="oldPassword" />
                                </div>
                              </div>
                              <div class="old_password">
                                <div class="left_titles">
                                  <span>{{ $t('hj151') + ':' }}</span>
                                </div>
                                <div class="right_password_input">
                                  <input type="password" v-model="newPassword" />
                                </div>
                              </div>
                              <div class="old_password">
                                <div class="left_titles">
                                  <span>{{ $t('hj152') + ':' }}</span>
                                </div>
                                <div class="right_password_input">
                                  <input type="password" v-model="cirNewPassword" />
                                </div>
                              </div>
                              <div class="btn_setting" @click="changeLoginPsd()">
                                <span>{{ $t('hj153') }}</span>
                              </div>
                            </div>
                          </van-popup> -->
    <div class="head">
      <div class="tbox">
        <!-- <img src="~@/assets/img/yifu.png" />
        <img src="~@/assets/img/lingdang.png" /> -->
      </div>
      <div class="userHead">
        <div class="tl">
          <img class="touxiang" src="~@/assets/imgRed/touxiang.jpg" />
          <div class="mingzi">
            <p>{{ userInfo.phone ? userInfo.phone : userInfo.phone }}</p>
            <div class="feae" v-if="(!showBtn&&this.$store.state.userInfo.isActive != 1)">
              <img src="~@/assets/imgRed/jinse.png" />
              <span>已实名</span>
            </div>
            <div class="feae" v-if="(showBtn)" @click="$router.push('/smrz')">
              <img src="~@/assets/imgRed/yinse.png" />
              <span>未实名</span>
            </div>
          </div>
        </div>
        <div class="tr" @click="$router.push('/setup')"></div>
      </div>

    </div>
    <div class="zijk">
      <h5>
        账户总资产(沪深账户)
      </h5>
      <div class="kunk">
        <van-circle v-model="currentRate" :rate="rate" :clockwise="false" class="daxiao" color="#5d7dfb"
          layer-color="#e6e6e6" stroke-width="60" />
        <div class="zican">

          <p v-if="this.$store.state.settingForm.indexDisplay && !this.$store.state.settingForm.futuresDisplay"
            class="yans">
            ¥{{ $store.state.hide ? '****' : Number($store.state.userInfo.userAmt +
                $store.state.userInfo.userIndexAmt).toFixed(2)
            }}
          </p>
          <p v-else-if="!this.$store.state.settingForm.indexDisplay && this.$store.state.settingForm.futuresDisplay"
            class="yans">
            ¥{{ $store.state.hide ? '****' : Number($store.state.userInfo.userAmt +
                $store.state.userInfo.userFuturesAmt).toFixed(2)
            }}
          </p>
          <p v-else-if="!this.$store.state.settingForm.indexDisplay && !this.$store.state.settingForm.futuresDisplay"
            class="yans">¥&nbsp;&nbsp;{{ $store.state.hide ? '****' :
                Number($store.state.userInfo.userAmt).toFixed(2)
            }}</p>

          <p>账户总资产</p>
        </div>
      </div>
      <div class="boxk">
        <div class="bdan">
          <p style="display: flex; align-items: center;">
            <img
              src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAAXNSR0IArs4c6QAABVVJREFUWEfFl2tsk2UUx3/Pu7Zrd2FjwNaNSwYbasAAKsotYKYSEUk0GAKSoEZGApKYaKKSGMOInyYfQINfQESjYUrw8gGMCmQagqiJiiMgchsXGYONiVvbbe3Wx5w+79Z27dsVQ+JJlqXt85zzf87lf85RZCmaEwWQ9zhEa0DNACYCRfb1f4Bm0MfAaoTQfsXUQDaq1XCHNGerwXoNeBrIH+68/XsQaIBovaL6bKY7jgA0zV6I1oF6CfBkaXjosTDorWBtVEzsSacjLQDNmSrI2QuIq2+HHIPosnTeSAGguXAvRL8Cym6H5QQd18BarKj8NVFvEgA73j8AY26z8QF1bRCdm+iJQQCayz6IHAWmpxif4IIn82C+F6Z6wJ8DIyzQGjo1XO2D4xE43ANfBqGlPxP+JnDPVozvlkMJAM7Vg3o16WaVGzaXwBN5YAFX+uH3XrjcDzej5uhoC8a6YIYNTGx/HoRXOuBinwMQ/ZaiSirLALCT7g/APXhjZi586wefgnc6YWcXnI5kjswUN6wuhHUjIKRhQQucTHsnAtEpEgobwPkdQG2S9p8rjKsfboUzwxgeCusuN3xfDk1hWNjqBHqnYlKt0pwqBM/VFJIJVcKHAVjX/t/ycW8pLPBC6SWn+0EIlytN83LQn6Sc+qIMHvVBbTvszopVjQrx6foRsHUUNARgVVuGB6gVSnN+O7Am5dRDPjjkN18fDxtvNHbDiQj06uTjXmWSUO48UwB32qk0swV+6c3kwR0C4EdgVsqpoxVQ5YK3O2FVglJJ/pY+6NbmtT4LKnLi9SRx3xuEDcWmLBc55oCY/EkAiI9GpwDoqYT3u+CFG+YneZXE9A43jHdBgZLygYCGS31wKgJHeuKVIjnwoBfGOOaAaG0XAOKj1GZzwA8P5MLS63AoxhmZRQA+WwDTPCAhmes17p8v+e0oYWcAs3NBwiCv/KYbPgoYINfSsFzdSHi9CJSCE2HDAXe7ocCC/SFY2QadNnElY4kBSB8C8YC8YkcXLMs3cY45rd8wYUc/WArKckAI6NMgvHwjTsN5Cl4sgjeL4Ts7F1Kxx0KQPgklB4T91t8Al4I5uTA3N54D0guEhoWu9wRhxXXjLXF/oQVttrU1hbB9NDzfDru6hsYiloTpy3CgCoQJpQzTycZieGMkTLhkXl5kwUG/ASUMKDkglXJynAG0ICUfpAwdiGiAB8LaxF/+jvaCfB6QfWUwThrRFchVcLgcpnugPQoSgnl2L9g2Cp4rhIILQ54RIyIHKhYmfMwH+0KwJM8YkNr/MwIXhAeiUOMzHW92CxRbcHqcMSC9Y5bXcMDBbtgyCtYWgi8JQAjC/oFm9B6wOglebyXsCsDadiix4BGfmQek3OTVEmtpViJjLkJQm1mh0Q8lOVDbBh/YFH6kHPIt46m4mGYkn+1J6GRSO/5trDEscXPq61Ilorzub9h006iuFoA5JvNFBLhU1IYOqJfpPSYR0FMVVWecB5J5Xvi6DGSm2NYJHweM+4fKnlJYmm9KUM4llvtCHzSUmpK954rxkpHNikmx4WfoSCYlOW3QxmQ3bC2BRfZE9Jc9esn/jqjhzwoXLLfXBYn9ge7Y+7jfY3hEQC9uhfOD01ETeOcoKkJJABJCkTqUTnKZREzMASm5fg1d2oToVNiAuc9jeEOmJ2nj73ZBYNAtzkPpwKv/17E8DiK2jn2WFI7U6N/KN00QfSqrxSQOIraabbJXs/iweitmTbZvueXVLNGG5txke1xfCeRlaV/6927Q9VJqme4Mux3HPRJbz5eArrF3RlnPi+3fhQSagWOgZD3fl+16/i91NuTW6o8hHwAAAABJRU5ErkJggg=="
              style="width: 0.4272rem; height: 0.4272rem; margin-right: 0.1602rem;" />
            沪深冻结保证金
          </p>
          <h6>

            ¥ {{ userInfo.length != 0 ? ($store.state.userInfo.allFreezAmt + $store.state.userInfo.djzj).toFixed(2) : 0
            }}
          </h6>
        </div>

        <div class="bdan">
          <p style="display: flex; align-items: center;"><img
              src="data:image/png;base64,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"
              style="width: 0.4272rem; height: 0.4272rem; margin-right: 0.1602rem;"> 沪深可用资金</p>


          <h6 style="margin-left: 0.534rem;">
            <span class="bzz" v-if="!selectUserFlag">{{ '¥ ' + $store.state.userInfo.enableIndexAmt }}</span>
            <span class="bzz" v-else>{{ '¥ ' + $store.state.userInfo.enableAmt }}</span>
          </h6>
        </div>

        <div class="bdan">
          <p style="display: flex; align-items: center;"><img
              src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAAXNSR0IArs4c6QAAA/hJREFUWEfFl11sVEUUx38zu620yEdwaUWjUFEjKFoMKjaibJRGWkmMSFBfqU9Gow8Uw4ua+FJJ/HgwMVSNT9UYeRIbU0pKdC0lJrbpA9KUpCkVsWmBNrDttrt3xsyd/ea2965s47zc5M455/+fM+ecOUcQcOkebsUJNaN0FCHqQdcBq9Lq0yBG0HoAKXoIOT+JKNeDmBZ+Qvpn7iUsD6F5FVjuJ5/ejyP4lpRqE89zfjGdBQnoHpbhyPfRvANUBgQuFptH8ykV6j0RJeFlw5OAPslGlPwBqP+PwMVqAzhqn5c3biCgT/IoSnYCtWUCz5gZR6om8Sx/5NstIODed0j2AmvLDJ4xN4GjGvI9kSWge6liRp5G80hZwNdE4aEOSIxBfxMkJzNmB1mutosGZs2PHIEu2YagtSzgxsjDx6DmRWvu7AH4+5ucac1HolEdyhJIB92fQIUvgYrbILIbpn6D2RFvcRGCrZ2w5jlIXoXft8NMQTYmcdRmcxWuB3S3bEfT4gtuBB4/Ayu3WZfG7gEnXqhmwDe1Q2oaxj6zX0OieGm+Eo2qRegYK5iVlwIXmZ1XIGwKoIZf18PcxZxpIS24cw2GTPnQi50pTpVaJ3R3aD9afxfo9LX7oO4wzI1DeCVMdsLIh2lVAZsNeByG3vYDtzpCvCL0CXkUeN2XQO3LsP5gOqIvg6yELd/bOEhdger7ITkFQ28FA7eA7UKfCPWBfmJRAjV7YUNrDjwjXBGBZ/6xyZSaglORUsCN3hnjgQnAaBYu42ITxSbq72yB/ub8XLay4dXw9CXrjflx+OUO+3/TUah5KWfPuQ6xDV5nnDQE5jwfm8d6YZVxjIbYRkiMejsp0mzz/eLXMH3aytT/CJGmQvnukJf+/MIEdl61gWZWrA4SF3zDJCtQIgHvKzBBd9ebMHEcRo94g4dWgAzn9lLXQKdK8YC5ggBB6AV/yzrYMZZfzWH0Yxg+WAIBG4TB0rCYxLK74amiUvzXF3DujRIImDT0K0Qm1badAmfGUghVQ/9ue/KbJeAWIr9SbE76wOcwsCcX4ecP2xp/cwRmqFK328eoS36J4IBnpC0Vgcxj5BKwndBZz+d4aQgk0epB0ciwf0OyNASOiF3KbX4KW7K47DO9TMFVlJ/AIAn1pNiDG9X+TWllDTScg+Rly6tyLfRttVmx4wKIvEJkCtbwu7ClA2r3F74FPe4QtXBTmpH+X9vyLAkblMduuI7gr0Gx5CCO2htoMMmSMKNZUn6AcEcz/2bVm1wSzSclj2b5tnQX9yFlK5rXgOqATphF0wGqzaTaYjq+03GeR8x4/gJaR9GiHuGO56vT+1NoMYLQAwh3PD8edDz/F5zhrmiE4ANeAAAAAElFTkSuQmCC"
              style="width: 0.4272rem; height: 0.4272rem; margin-right: 0.1602rem;"> 持仓总盈利 </p>
          <h6 style="margin-left: 0.534rem;"> {{ '¥ ' + $store.state.userInfo.allProfitAndLose }}</h6>
        </div>

        <div class="bdan">
          <p style="display: flex; align-items: center;"><img
              src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAAXNSR0IArs4c6QAAA/hJREFUWEfFl11sVEUUx38zu620yEdwaUWjUFEjKFoMKjaibJRGWkmMSFBfqU9Gow8Uw4ua+FJJ/HgwMVSNT9UYeRIbU0pKdC0lJrbpA9KUpCkVsWmBNrDttrt3xsyd/ea2965s47zc5M455/+fM+ecOUcQcOkebsUJNaN0FCHqQdcBq9Lq0yBG0HoAKXoIOT+JKNeDmBZ+Qvpn7iUsD6F5FVjuJ5/ejyP4lpRqE89zfjGdBQnoHpbhyPfRvANUBgQuFptH8ykV6j0RJeFlw5OAPslGlPwBqP+PwMVqAzhqn5c3biCgT/IoSnYCtWUCz5gZR6om8Sx/5NstIODed0j2AmvLDJ4xN4GjGvI9kSWge6liRp5G80hZwNdE4aEOSIxBfxMkJzNmB1mutosGZs2PHIEu2YagtSzgxsjDx6DmRWvu7AH4+5ucac1HolEdyhJIB92fQIUvgYrbILIbpn6D2RFvcRGCrZ2w5jlIXoXft8NMQTYmcdRmcxWuB3S3bEfT4gtuBB4/Ayu3WZfG7gEnXqhmwDe1Q2oaxj6zX0OieGm+Eo2qRegYK5iVlwIXmZ1XIGwKoIZf18PcxZxpIS24cw2GTPnQi50pTpVaJ3R3aD9afxfo9LX7oO4wzI1DeCVMdsLIh2lVAZsNeByG3vYDtzpCvCL0CXkUeN2XQO3LsP5gOqIvg6yELd/bOEhdger7ITkFQ28FA7eA7UKfCPWBfmJRAjV7YUNrDjwjXBGBZ/6xyZSaglORUsCN3hnjgQnAaBYu42ITxSbq72yB/ub8XLay4dXw9CXrjflx+OUO+3/TUah5KWfPuQ6xDV5nnDQE5jwfm8d6YZVxjIbYRkiMejsp0mzz/eLXMH3aytT/CJGmQvnukJf+/MIEdl61gWZWrA4SF3zDJCtQIgHvKzBBd9ebMHEcRo94g4dWgAzn9lLXQKdK8YC5ggBB6AV/yzrYMZZfzWH0Yxg+WAIBG4TB0rCYxLK74amiUvzXF3DujRIImDT0K0Qm1badAmfGUghVQ/9ue/KbJeAWIr9SbE76wOcwsCcX4ecP2xp/cwRmqFK328eoS36J4IBnpC0Vgcxj5BKwndBZz+d4aQgk0epB0ciwf0OyNASOiF3KbX4KW7K47DO9TMFVlJ/AIAn1pNiDG9X+TWllDTScg+Rly6tyLfRttVmx4wKIvEJkCtbwu7ClA2r3F74FPe4QtXBTmpH+X9vyLAkblMduuI7gr0Gx5CCO2htoMMmSMKNZUn6AcEcz/2bVm1wSzSclj2b5tnQX9yFlK5rXgOqATphF0wGqzaTaYjq+03GeR8x4/gJaR9GiHuGO56vT+1NoMYLQAwh3PD8edDz/F5zhrmiE4ANeAAAAAElFTkSuQmCC"
              style="width: 0.4272rem; height: 0.4272rem; margin-right: 0.1602rem;"> 账户总盈利</p>
          <h6 style="margin-left: 0.534rem;">{{ '¥ ' + $store.state.userInfo.allProfitAndLose }}</h6>
        </div>
      </div>

      <div class="congz"><a @click="$router.push('/recharge')">银转证</a><a @click="$router.push('/withdraw')"
          class="tx">证转银</a></div>



    </div>


    <div class="tile" style="font-size: 14px;">常用功能</div>


    <div class="usb">
      <div @click="$router.push('/loginPassword')" class="bl">
        <img
          src="data:image/png;base64,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">
        <p>登录密码</p>
      </div>
      <div @click="$router.push('/setPassword')" class="bl">
        <svg t="1670394224864" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4406" width="64" height="64"><path d="M874.4 394.2c-6.5-100.8-19.1-187.6-19.6-191.3-1.6-10.9-9.1-20-19.4-23.8L522.2 65.8c-6.6-2.4-13.9-2.4-20.5 0l-313 113.4c-10.4 3.8-17.8 12.9-19.4 23.8-0.5 3.6-13.2 90.5-19.6 191.3-3.8 59.6-4.6 111.6-2.4 154.6 2.9 57.3 11.1 98.3 25 125.6 20.5 40.1 79.8 97.9 181.3 176.8 72.7 56.5 140.9 103.1 141.6 103.6 5.1 3.5 11 5.2 16.9 5.2 5.9 0 11.8-1.7 16.9-5.2 0.7-0.5 68.9-47.1 141.6-103.6 101.4-79 160.7-136.8 181.2-176.9 13.9-27.2 22.1-68.3 25-125.5 2.2-43 1.4-95-2.4-154.7z m-648.2-165l226.5-82v306.7h-246c0.6-17 1.5-35.4 2.7-55.4 4.7-72.4 12.8-138.8 16.8-169.3z m407.6 574.7c-49.3 38.4-96.5 72.1-121.2 89.3V513.5h305.2c-1.2 82.7-11.9 118.6-19.6 133.7-9 17.9-42.5 61.9-164.4 156.7z" fill="#5d7dfb" p-id="4407"></path></svg>
        <p>资金密码</p>
      </div>
      <div class="bl" @click="$router.push('/FundingDetails')"><img
          src="data:image/png;base64,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">
        <p>资金流水</p>
      </div>
    </div>

    <div @click="$router.push('/bankCard')"
      style="display: flex; align-items: center; justify-content: space-between; margin: 0.534rem 0.267rem;"><span
        style="font-size: 14px;"> 金融卡 </span>
      <div style="font-size: 14px;"> 管理 <img
          src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAwAAAAWCAYAAAD0OH0aAAAAAXNSR0IArs4c6QAAAbxJREFUOE+F079rFEEUB/DvW3+g/8L8NQpBCQaMGFGL2Oj8uE0l2phCxiI2ipUWM3NgEIJyRAttksZGCy20sRL/A0HOC2owrPNkjnfBy+2d2+2+72ffY/YtpZTWmXkp51w7557gPxfFGHcBHAPwh4iWtdZPZ5kCbgK4V0LM3BDRJWPM82mISiHGeBvAHQnt5ZyXnHOv2tAQCLoL4JZ0+k1EZ40x2wfRPhD0AMB1Qbs554VOp/P6XzQGBD0CUEvoBzPPW2vfjtAEYGZKKSUAV6XTDoBT1tr35X4ClIfe+0optQ5gWd7cZ+Y5a+3HVlBCvV7v0GAw2GDmi9LpG4CTU0EJhRCOFEtEi9Lpy0zgvT+qlHoB4Ix0+TwVeO8PK6V6AM5J+GtVVSdaQZm/3+9vENHY/NbaTxNATugxgCsy9/emaebquv4wcazlG3S73cjM1yS80zTN6bqu37V+uBDCQyJakeJPIprXWr9pXY2U0n1mvjHao6qqFrTWY3u0P1IIYY2IVkebysyLzrmtg5s6BCGEVSJak+IegAvGmJdt4RH4RUTHy9/GzJedc5vTwkMQY4wAzgNYMcY8mxUutb/p+c1U0aFG2AAAAABJRU5ErkJggg=="
          style="width: 0.1602rem; height: 0.2937rem;"></div>
    </div>

    <div style="overflow-x: auto; display: flex; margin: 0px 0.1rem;"></div>

    <div class="tile" style="font-size: 14px;">其它功能</div>

    <ul class="ganh" style="font-size: 14px;">
      <li @click="$router.push('/about?e=1')">
        <div class="le"><img
            src="data:image/png;base64,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">
          <p>版本更新</p>
        </div><span></span>
      </li>
      <li @click="$router.push('/about?e=2')">
        <div class="le"><img
            src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACgAAAAoCAYAAACM/rhtAAAD6klEQVRYhc2YTWhVRxTH/3NfFm5sNxVFdxakhaL4xYuIduPKNqGbQnEhWHDZrWRbCqIrF7oTFBWxha7is4t007SV1iR+YIpgXUmycSM0RZPcOTNH5n5l3ryZefP0EfOH5H7P+d1zzzlz5gk4OvzFH5cBfAsgs69w/U+ItWNzCNHs19viHnaO68eL+7nnfs2sBcSVuc7oadtuF0TxEPOp9YYz+5nIMgZOuTwj7gkhRKsCfQFgpguOuRl4x/YdxzVzca8256trrLk41lqXntG6OH796vW0JLnUA8dswEdZYEttOwpogc7dvXNkPHT90nVeJoWWUoAkQGlASkCqtW2eEyQRFCmsrK58d/PCR/O+sfZ/+fevAjjGnmtBQN/NtgxQChxJs5Ugouh4IXtewOazRmTsuXB5DuSEAsaFkxHAMj79JnsA3YQIyYbLZXkcgsvlMD2YAAeE4QyMiTkDJ6UsYRM96LPbU2bcUhLSIHCkVBSwsesxGo7BPlrJyySx4cxnJUVeOAPeF87jQX8MVkU1JgO3KjkYc6oCM4AGztTCoMpK7b3q9aA9Q4TkSwif57TS5b6JiZgHU7M4BQ5FDMq+MVfD1edi4oHKTEqSVHGVAqe0Lqa0kGK2erM4MUlkbjyYu3B/kVI/a6XZ9pyJx5gHG6eklBm3CwkCkpRNDBI9J6VOLCwuHp66tutrUuooEc0aqLpZyLIsj41XwqXUQYRrki0iOieJnimlJpTWn/5y5eNb81OfF09N//jZn/8tLbW11ic180MA11dWV//tB5fWLLj9XEA/Xdx2FsDZ0PV/po6aIW5Uf30k0pMkBW7YioXVW8fgMMVY66z7Aq43XG0v/RMnNgvDVGx6DTYL7s37j9/dmbVaP2jmzb7Fkrtosl/UrnNCZAuSaOLJnfb/tr0sNYvd1VetrNWaYOBEJrKuDtjeNnCZu/qr4CqI1sjIIwCXm3eKZHFPDPrgUK7cJgXE0tt6ziolCwz87jqlTpQEDzrr1kr3O4c6AD70vOQ7q3yvAbJ4vevgQFkcqoPtr+Y2SaJvhBAfhD5rsWBnfvS40+76hP0Uc4h/XexxNyn1vRDiTCzmUCQC9O7x2U8eTx58NghgOUrioslXZpj5ZWJCLDNjORXOMpy+JvF11Q86o+f3jd37zdRBaO76rcbJ1qfztw8uDgPOCxhr+R/cbt8bzHCiIrNXsFl4bx2Now1RZgZak7wvD4a0MQp1RBuiYY0pXKg1H9g7NjOJ+MTfNU2JwPlq0Fi27gkx+uqgEoyWyLKtAMZCLROcCd7u5+zzzQzR9WO6X8zcs3j2tVtXIYReJ8+tOYbZ2LzadRLAG2TYR+L1rz8MAAAAAElFTkSuQmCC">
          <p>用户协议</p>
        </div><span></span>
      </li>
      <li @click="$router.push('/about?e=3')">
        <div class="le"><img
            src="data:image/png;base64,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">
          <p>隐私协议</p>
        </div><span></span>
      </li>
      <li @click="$router.push('/about?e=4')">
        <div class="le"><img
            src="data:image/png;base64,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">
          <p>关于我们</p>
        </div><span></span>
      </li>
    </ul>



  </div>
</template>

<script>
import * as api from "@/axios/api";
import { Toast } from "mint-ui";
import { isNull, pwdReg } from "@/utils/utils";
import { MessageBox } from 'mint-ui'
import { compress } from "@/utils/imgupload";

export default {
  name: "newUser",
  data() {
    return {
      name: "大狗子",
      selectUserFlag: true,
      settingDialog: false,
      oldPassword: "", // 旧密码
      newPassword: "", // 新密码
      cirNewPassword: "", //确认新密码
      userInfo: [],
      onlineService: "",
      currentRate: 100,
      rate: 0,
      showBtn: true,
    };
  },
  components: {
  },
  created() {
    this.getUserInfo();
    this.getInfoSite();
  },
  methods: {
    goOnline() {
      if (navigator.vibrate) {
        // 支持
        navigator.vibrate([55]);
      }
      this.$router.push('/service');
    },
    async getInfoSite() {
      let data = await api.getInfoSite()
      if (data.status === 0) {
        this.onlineService = data.data.onlineService
      } else {
        Toast(data.msg)
      }
    },
    goWall() {
      if (this.userInfo.length == 0) {
        this.$store.commit('dialogVisible', true);
        return;
      }
      this.$router.push('/wallet')
    },
    handleZh() {
      this.selectUserFlag = !this.selectUserFlag;

      if (this.userInfo.length == 0) {
        this.$store.commit('dialogVisible', true);
        return;
      }
      if (navigator.vibrate) {
        // 支持
        navigator.vibrate([55]);
      }
    },
    async getUserInfo() {
      // 获取用户信息
      let data = await api.getUserInfo();
      if (data.status === 0) {
        // 判断是否登录
        this.$store.commit('dialogVisible', false);
        this.$store.state.userInfo = data.data;
        this.userInfo = data.data;
        this.rate = data.data.enableAmt / data.data.userAmt * 100;
        if (data.data.isActive === 1 || data.data.isActive === 2) {
          this.showBtn = false
        }
      } else {
        this.$store.commit('dialogVisible', true);
      }
    },
    goToTopUp() {
      if (this.userInfo.length == 0) {
        this.$store.commit('dialogVisible', true);
        return;
      }
      if (navigator.vibrate) {
        // 支持
        navigator.vibrate([55]);
      }
      this.$router.push("/wallet");
    },
    handleOutLoginClick() {
      // 退出登录
      MessageBox.confirm(this.$t('hj149') + '?', this.$t('hj165'), {
        confirmButtonText: this.$t('hj161'),
        cancelButtonText: this.$t('hj106'),
      }).then(() => {
        this.toRegister();
      }).catch(() => {

      });
    },
    goToSettings() {
      if (this.userInfo.length == 0) {
        this.$store.commit('dialogVisible', true);
        return;
      }
      // 每次打开dialog 清空密码数据
      this.settingDialog = !this.settingDialog;
      if (this.settingDialog) {
        this.oldPassword = "";
        this.newPassword = "";
        this.cirNewPassword = "";
      }
    },
    handleGoToTransfer() {
      if (this.userInfo.length == 0) {
        this.$store.commit('dialogVisible', true);
        return;
      }
      this.$router.push("/transfers");
    },
    handleGoToAuthentication() {
      if (this.userInfo.length == 0) {
        this.$store.commit('dialogVisible', true);
        return;
      }
      this.$router.push("/authentications");
    },
    handleGoToBankCard() {
      if (this.userInfo.length == 0) {
        this.$store.commit('dialogVisible', true);
        return;
      }
      this.$router.push("/bankCard");
    },
    async toRegister() {
      // 注销登陆
      window.localStorage.removeItem("USERTOKEN"); // 清空本地存储 USERTOKEN字段
      this.clearCookie();
      let data = await api.logout();
      if (data.status === 0) {
        // Toast(data.msg)
        this.$router.push("/login");
      } else {
        Toast(data.msg);
      }
      this.$router.push("/login");
    },
    async changeLoginPsd() {
      // 修改密码
      if (
        isNull(this.oldPassword) ||
        isNull(this.newPassword) ||
        isNull(this.cirNewPassword)
      ) {
        Toast(this.$t('hj154'));
        this.settingDialog = false;
      } else if (!pwdReg(this.newPassword)) {
        Toast(this.$t('hj19'));
        this.settingDialog = false;
      } else {
        // 修改密码
        if (this.newPassword === this.cirNewPassword) {
          let opts = {
            oldPwd: this.oldPassword,
            newPwd: this.newPassword
          };
          let data = await api.changePassword(opts);
          if (data.status === 0) {
            this.changeLoginPsdBox = false;
            Toast(data.msg);
            this.settingDialog = false;
          } else {
            Toast(data.msg);
            this.settingDialog = false;
          }
        } else {
          Toast(this.$t('hj155'));
          this.settingDialog = false;
        }
      }
      if (navigator.vibrate) {
        // 支持
        navigator.vibrate([55]);
      }
    }
  }
};
</script>

<style scoped>
.user_page {
  background-color: #fff;
  font-size: 14px;
  padding-bottom: 1.3rem;
}

.head {
  height: 5.67rem;
  width: 100%;
  background: url('~@/assets/imgRed/bjing.b7cbcd97.png') no-repeat 50%;
  background-size: 100%;
  position: relative;
}

.tbox {
  height: 1.1748rem;
  width: 9.345rem;
  margin: 0 auto;
  text-align: right;
}

.tbox img {
  width: 0.5073rem;
  height: 0.5073rem;
  margin-top: 0.3204rem;
  margin-left: 0.2136rem;
}

.userHead {
  display: flex !important;
  width: 8.6775rem !important;
  margin: 0 auto !important;
  -webkit-box-pack: justify !important;
  -ms-flex-pack: justify !important;
  justify-content: space-between !important;
  margin-top: 0.534rem !important;
}

.tl {
  display: flex;
}

.touxiang {
  width: 1.602rem;
  height: 1.602rem;
  border-radius: 50%;
}

.tr {
  width: 0.534rem;
  height: 0.534rem;
  background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACgAAAAoCAYAAACM/rhtAAAAAXNSR0IArs4c6QAAAz9JREFUWEflmMuLjWEcxz9fSaFElKwIYcZtJsol17BgRVhIzUJYKVsLMprxD7CRiYXbBmHBRpFLGguaMcp1xEYWRAkl+emn953OvOd9znnf886Zjjx1Nuf9Xb7P83ue7+8iCi4zOwnsCZg5Jml/ERcqouy6ZvYEmB+w0yuppYiPegPsk7SgbgDNbDSwA7gi6XOaIzN7DcwIgOiXNDOgNwHYCpyX9CO0ieAJmtkY4DqwBugB1kv6VGrIzDy0/m1EwMFvoEVSX0JvEnDTvwF3gE2SvqfZSAWYABfr9QKbJb2N7t5y4EyF04v1+oE2SQ8ivWnANaA09EGQZQAD4GJnP4FHwBTAHeVZ74APQCswKkUxFeQggFXA5QFTq2wZyCTAXcCpWq0PkZ5fh7OxrSRAf1m3gYVD5CyvGX9wqyR9TQUYXWJ/YbcqkG9ep1nl/aWvTTJF6BX7A3gRuMxZHeaR88c3O2aIUsVKPNgNLMnjpYBst6RlmXkwCvUzYE4Bp3lUn0tqygzQzFYA93J4eBORthcOvpyE24DpOWyslHQ/KZ9G1M0R06fm0IQBA44CHZL8Hg0sM3MybgcOAFmKEs/pWyQ9HXQHzewEsDj6c1yUukK5NbnBdklHKp2SmR2OgGY5TM/dL4FvkXCPzMxPoZblO26S9KsKwJGA3+csESkzVQRgp6RDWXZmZh3AwSyyZXewwAlul3Qpi1Mz2wZczCL7XwFs+BC/ApqH45F0RUWkh38sMKtCCT/cNPM4jajnAlcz0oJTVKf/AkTtL9d/WYjaWwMn6kH9S6iaqSXVnQPiLODN1M66pLo4hmbWuMWCgzSz4Sy3HkpamsaT/17BamYTo5K/0Miihqzhffe6iiW/mY2PmqZCA58awMUq3jR5X/Il/qMR287dkgZa37TG/QawusApFFG9C2wsndOERh8hkF41exgmA1NzIvHRx3tgUaBbLAPn9isNj5Ig04ZHp71drAI0bXjkmap0OJB9eFRC1D5+i0H6qW2Q9LEUjJnNAxx4pfFbq6S4mfqrbmbFxm8lIH2A6Snrch0HmBdCs8FgiPPcrSoz6vqOgLMANTMPcYjUGwLgcWBfYDNdkvZm2WhI5g/TmWFQBGMuAAAAAABJRU5ErkJggg==) no-repeat 50%;
  background-size: 100%;
  margin-top: 0.534rem;
}

.feae {
  width: 2.0025rem !important;
  height: 0.534rem !important;
  background: #5d7dfb !important;
  border-radius: 10px !important;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex !important;
  -webkit-box-align: center !important;
  -ms-flex-align: center !important;
  align-items: center !important;
  -ms-flex-pack: distribute !important;
  justify-content: space-around !important;
  margin-top: 0.267rem !important;
}

.feae img {
  width: 0.3738rem;
  height: 0.3738rem;
}

.tl div {}

.mingzi {
  margin-left: 0.3204rem !important;
  color: #fff !important;
  font-size: .3738rem !important;
  margin-top: 0.267rem !important;
}

.zijk {
  width: 9.345rem;
  padding-bottom: 0.1335rem;
  background: #fff;
  -webkit-box-shadow: 0 0.04626rem 0.1335rem 0 rgb(0 0 0 / 10%);
  box-shadow: 0 0.04626rem 0.1335rem 0 rgb(0 0 0 / 10%);
  border-radius: 0.267rem;
  margin: 0 auto;
  margin-top: -1.4685rem;
  z-index: 2;
  position: relative;
}

.zijk h5 {
  margin-left: 0.267rem;
  padding-top: 0.5073rem;
  font-weight: 500;
  color: #333;
  font-size: 0.3204rem;
}

.kunk {
  padding: 0.4005rem;
  margin: 0 auto;
  margin-top: 0.5073rem;
  position: relative;
}

.boxk {
  width: 8.01rem;
  margin: 0 auto;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -ms-flex-wrap: wrap;
  flex-wrap: wrap;
  margin-top: 0.534rem;
}

.bdan {
  width: 50%;
  margin-bottom: 0.4272rem;
}

.bdan h6 {
  color: #ea3544;
  font-size: 0.3738rem;
  margin-top: 0.0801rem;
  font-weight: 700;
  margin-left: 0.534rem;
}

.congz {
  width: 95%;
  margin: 0 auto;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-pack: justify;
  -ms-flex-pack: justify;
  justify-content: space-between;
  margin-top: 0.2937rem;
  padding-bottom: 10px;
}

.congz a {
  width: 48%;
  height: 1.068rem;
  background: #ea4445;
  border-radius: 0.1335rem;
  color: #fff !important;
  font-size: 0.3738rem;
  text-align: center;
  line-height: 1.068rem;
}

.tx {
  background: #17b780 !important;
}

.tile {
  margin: 0.534rem 0.267rem;
}

.usb {
  width: 9.345rem;
  height: 2.5365rem;
  margin: 0 auto;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-pack: justify;
  -ms-flex-pack: justify;
  justify-content: space-between;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  background: #fff;
  -webkit-box-shadow: 0 2px 5px 0 rgb(0 0 0 / 10%);
  box-shadow: 0 2px 5px 0 rgb(0 0 0 / 10%);
  border-radius: 10px;
}

.bl {
  width: 25%;
  text-align: center;
}
.bl svg{
  width: 0.7743rem;
    height: 0.7743rem;
}
.bl img {
  width: 0.7743rem;
  height: 0.7743rem;
}

.bl p {
  color: #333;
  font-size: 0.3738rem;
  margin-top: 0.267rem;
}

.ganh {
  -webkit-box-shadow: 0 2px 5px 0 rgb(0 0 0 / 10%);
  box-shadow: 0 2px 5px 0 rgb(0 0 0 / 10%);
  border-radius: 10px;
  margin: 0.267rem;
  padding: 0.267rem;
  list-style: none;
}

.ganh li {
  border-bottom: 0.0267rem solid #ccc;
  height: 1.2282rem;
  margin: 0 auto;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-pack: justify;
  -ms-flex-pack: justify;
  justify-content: space-between;
}

.le {
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  margin-top: 0.3471rem;

}

.ganh li .le img {
  width: 0.534rem;
  height: 0.534rem;
}

.ganh li .le p {
  color: #333;
  font-size: 0.3738rem;
  line-height: 0.534rem;
  margin-left: 0.1602rem;
}

.ganh li span {
  width: 0.1602rem;
  height: 0.2937rem;
  background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAwAAAAWCAYAAAD0OH0aAAAAAXNSR0IArs4c6QAAAXZJREFUOE+F0zEvBEEUB/D/OyHhU8kpiMiJHKK6Rt7MJjSiUlGpRKOatwmJQthIJKKgEAUFBYVKfAUkS+IKyc2TuSzZc3Nnup19v83Oe/8hEdkDMEtES8y8j38WicgngBEALVVtWGsP+hlyzq0S0VZR1CKiBWY+7oUovEjTdF1VN4qiLwB1Y8xZDLVBWCKyCWCthGrGmIu/6BcUaBvASlHUVNUpa+1VGXUAVQ1N2AkdK6Fxa+3ND+oAYTOgNE1TAItF0buqBnQXnrtA2MyybCDP810AjRKqWmsfoqCEwkzmC/SqqtWeIBQ55waJ6AjATIGe+4Isy4byPD8BMFmAp54g8vUX7/1ov0P//j8RvQGoMvNjF4h1iIjGmPm+q62RGXwQ0Tgz33YNLjZlIppg5utoNESkI0fe+1qSJJfR8P1Nqvd+OkmS82i8RSREOkQ7rHAX5owxp7Hi9qFFpAlgOFzR/25bGzjnXKVSqavqsjHmsNeXf/a/AbF8tU103ogbAAAAAElFTkSuQmCC) no-repeat 50%;
  background-size: 100%;
  margin-top: 0.534rem;
}

.daxiao {
  width: 100% !important;
  height: 100%;
  position: absolute;
  left: 0;
  top: 0;
}

.zican {
  padding: 1.069335rem;
  margin: 0 auto;

  position: relative;
  font-size: 14px;
}

.yans {
  color: #5d7dfb !important;
  font-size: 0.3738rem !important;

}

.zican p {
  text-align: center;
  color: #333;
  font-size: 0.3204rem;
  padding-top: 0.0835rem;
}
</style>


