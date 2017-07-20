import riot from 'rollup-plugin-riot'
import nodeResolve from 'rollup-plugin-node-resolve'
import commonjs from 'rollup-plugin-commonjs'
import buble from 'rollup-plugin-buble'
import postcss from 'postcss'
import postcssCssnext from 'postcss-cssnext'

export default {
  entry: 'riot/index.js',
  dest: 'public/javascripts/bundle.js',
  plugins: [
    riot({
      style: 'cssnext',
      parsers: {
        css: { cssnext }
      }
    }),
    nodeResolve({ jsnext: true }),
    commonjs(),
    buble()
  ],
  format: 'iife'
}

/**
 * Transforms new CSS specs into more compatible CSS
 */
function cssnext (tagName, css) {
  css = css.replace(/:scope/g, ':root')
  css = postcss([postcssCssnext]).process(css).css
  css = css.replace(/:root/g, ':scope')
  return css
}
