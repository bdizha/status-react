(ns status-im.ui.components.svgimage
  (:require [status-im.ui.components.react :as react]
            [reagent.core :as reagent]))

(defn html [uri width height]
  (str
   "<!DOCTYPE html>\n
   <html>
   <head>
   <style type=\"text/css\">
   img {
        width: 100%;
        height: 100%;
        margin: 0 auto;
        }

   div {
        width: " (if width (str width "px") "auto")
   "height: " (if height (str height "px") "auto")

   "}
    body {margin: 0;}

    </style>
    </head>
    <body>
    <div>
    <img src=" uri " align=\"middle\" />
    </div>
    </body>
    </html>"))

(defn svgimage [{:keys [style source]}]
  (let [width (reagent/atom nil)
        {:keys [uri k] :or {k 1}} source]
    (fn []
      [react/view {:style     style
                   :on-layout #(reset! width (-> % .-nativeEvent .-layout .-width))}
       [react/web-view
        {:scroll-enabled     false
         :scales-page-to-fit false
         :style              {:width @width :height (* @width k) :background-color :transparent}
         :source             {:html (html uri @width (* @width k))}}]])))
