(ns sketches.util.math
  (:require [quil.core :as q]))

(defn random
  "A random number between 0 (included) and 1 (excluded)."
  []
  (. js/Math random))

(defn random-in
  [min max]
  (+ min (* (- max min) (random))))

(defn random-points
  [w h nb]
  (->> (repeatedly random)
      (take nb)
      (partition 2)
      (map (fn [[x y]] [(* w x) (* h y)]))))

(defn msin
  [frame period]
  (let [t (* frame (/ (* 2 q/PI) period))]
    (/ (+ 1 (q/sin t)) 2)))

(defn spike
  "f(0) = 0, f(0.5) = 1, f(1) = 0, linear interpolation in between."
  [t]
  (if (< t 0.5)
    (* 2 t)
    (- 1 (* 2 (- t 0.5)))))

(defn mcycle
  [period frame]
  (/ (mod frame period) period))

(defn bpm-period-in-frames
  [bpm fps]
  (let [seconds-in-a-beat (/ bpm 60)]
    (* seconds-in-a-beat fps)))

(defn slide
  [offset t]
  (if (< t offset)
    (+ t offset)
    (- t offset)))

(defn barycentre
  [x1 y1 x2 y2 x3 y3]
  [(/ (+ x1 x2 x3) 3)
   (/ (+ y1 y2 y3) 3)])

(defn scale-vec
  [v scale]
  [(* scale (first v)) (* scale (second v))])

(defn add-vec
  [& vecs]
  [(->> vecs (map first) (reduce +))
   (->> vecs (map second) (reduce +))])
