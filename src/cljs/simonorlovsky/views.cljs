(ns simonorlovsky.views
  (:require
   [re-frame.core :as re-frame]
   [simonorlovsky.subs :as subs]))

(defn footer []
 [:footer.footer
  [:div.container
   [:span.text-muted.pull-right "Made with ❤️ in Philly"]]])

(defn navbar []
 [:div
  [:nav.navbar.navbar-expand-lg.navbar-light.bg-blue
   [:a.navbar-brand {:href "#"} "Simon Orlovsky 🥑"]
   [:button.navbar-toggler {:type "button" :data-toggle "collapse" :data-target "#navbarSupportedContent" :aria-controls "navbarSupportedContent" :aria-expanded "false" :aria-label "Toggle navigation"}
    [:span.navbar-toggler-icon]]
   [:div.collapse.navbar-collapse {:id "navbarSupportedContent"}
    [:ul.navbar-nav.mr-auto
     [:li.nav-item
      [:a.nav-link {:href "/#"} "home"]]
     [:li.nav-item
      [:a.nav-link {:href "/#about"} "about"]]]]]])




;; about
(defn about-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div
     [navbar]
     [:div.container
      [:h2.mt-5 "About Me"]
      [:p "Software Engineer based in Philadelphia, PA. Writes code for " [:a {:href "https://getcrossbeam.com/"} "Crossbeam"] ", a tool to faciliate data sharing based in Center City."]
      [:p "I grew up in California and studied computer science at Carleton College. I have worked as a software engineer for a variety of YC and venture funded companies like " [:a {:href "https://uplift.com"} "Uplift"] ", " [:a {:href "https://assemblyai.com/"} "AssemblyAI"] ", " [:a {:href "https://spin.pm"} "Spin"]", " [:a {:href "https://submittable.com"} "Submittable"] "."]
      [:p "Known to enjoy a ripe avocado from time to time."]]
     [footer]]))

;; projects
(defn fifty-half-marathons-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div
     [navbar]
     [:div.container
      [:h1.mt-5 "Fifty Half Marathons in Fifty States"]
      [:iframe {:src "/map.html" :width "800px" :height "500px"}]
      [:h2 "Up Next 🔜"]
      [:ul
       [:li "New York"]
       [:li "Virginia"]
       [:li "DC"]]
      [:h2 "Complete ✅"]
      [:ul
       [:li "Pennsylvannia 12/8/2019"]
       [:li "New Jersey 12/14/2019"]
       [:li "California 1/2/2020"]
       [:li "Delaware 1/12/2020"]
       [:li "Maryland 1/26/2020"]]]
     [:br]
     [footer]]))


;; home
(defn home-panel []
  [:div
   [navbar]
   [:div.container
    [:h2.mt-5 "Active Projects"]
    [:ul
     [:li [:a {:href "/#fifty-half-marathons"} "50 Half Marathons in 50 States"]]
     [:li [:a {:href "https://pomocado.com"} "Pomocado"]]]]
   [footer]])

(defn- panels [panel-name]
  (case panel-name
    :home-panel [home-panel]
    :fifty-half-marthons-panel [fifty-half-marathons-panel]
    :about-panel [about-panel]
    [:div]))

(defn show-panel [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [::subs/active-panel])]
    [show-panel @active-panel]))
